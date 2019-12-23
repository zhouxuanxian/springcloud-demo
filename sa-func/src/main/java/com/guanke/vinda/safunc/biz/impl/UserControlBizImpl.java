package com.guanke.vinda.safunc.biz.impl;

import com.guanke.vinda.safunc.biz.DailyBiz;
import com.guanke.vinda.safunc.biz.SaleReportBiz;
import com.guanke.vinda.safunc.biz.UserControlBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.safunc.mapper.CxGtReimEntityMapper;
import com.guanke.vinda.safunc.mapper.GkEmployeeMapper;
import com.guanke.vinda.safunc.mapper.GkVisitEntityMapper;
import com.guanke.vinda.safunc.service.SaUserInfoService;
import com.guanke.vinda.safunc.service.SaUserTimeRecodeService;
import com.guanke.vinda.samodels.model.entity.GkEmployeeEntity;
import com.guanke.vinda.samodels.model.entity.SaUserInformationEntity;
import com.guanke.vinda.samodels.model.entity.SaUserTimeRecodeEntity;
import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制相关业务处理
 *
 * @author Nicemorning
 */
@Service
public class UserControlBizImpl implements UserControlBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControlBiz.class);

    private final GeneralFeignService generalFeignService;

    private final SaUserInfoService saUserInfoService;

    private final SaUserTimeRecodeService saUserTimeRecodeService;

    private final DailyBiz dailyBiz;

    private final GkEmployeeMapper gkEmployeeMapper;
    private final SaleReportBiz saleReportBiz;
    private final CxGtReimEntityMapper cxGtReimEntityMapper;
    private final ParamsConfig paramsConfig;

    private final GkVisitEntityMapper gkVisitEntityMapper;

    public UserControlBizImpl(SaUserInfoService saUserInfoService,
                              SaUserTimeRecodeService saUserTimeRecodeService,
                              GkEmployeeMapper gkEmployeeMapper,
                              GeneralFeignService generalFeignService,
                              SaleReportBiz saleReportBiz,
                              CxGtReimEntityMapper cxGtReimEntityMapper,
                              DailyBiz dailyBiz, ParamsConfig paramsConfig,
                              GkVisitEntityMapper gkVisitEntityMapper) {
        this.saUserInfoService = saUserInfoService;
        this.saUserTimeRecodeService = saUserTimeRecodeService;
        this.gkEmployeeMapper = gkEmployeeMapper;
        this.generalFeignService = generalFeignService;
        this.dailyBiz = dailyBiz;
        this.saleReportBiz = saleReportBiz;
        this.cxGtReimEntityMapper = cxGtReimEntityMapper;
        this.paramsConfig = paramsConfig;
        this.gkVisitEntityMapper = gkVisitEntityMapper;
    }

    /**
     * 通过用户的临时登录code获取该用户的个人信息，并判断该用户是否允许登录
     *
     * @param code 用户的微信临时CODE
     * @return 如果存在则返回该用户的信息，否则只返回openId，如果返回null则说明该用户已被移出
     */
    @Override
    public Map<String, Object> getUserInfoByCode(String code) {
        Map<String, Object> result = (Map<String, Object>) generalFeignService.getUserInfoByCode(code).getData();
        String phone = String.valueOf(result.get("phone"));
        String avatar = String.valueOf(result.get("avatar"));
        Map<String, Object> crmUserInfo;
        if (StringUtils.isNotBlank(phone)) {
            // 查找系统内是否存在该用户
            SaUserInformationEntity userInfo = saUserInfoService.getUserInformationByPhone(phone);
            if (userInfo == null) {
                // 用户是第一次登陆,判断该用户在CRM中是否为外部人员且其为在职状态
                if (!saUserInfoService.isOuterUserInCrmByPhone(phone)) {
                    return null;
                }
                crmUserInfo =
                        saUserInfoService.selectCrmUserInfoByPhone(phone);
                String posId = String.valueOf(crmUserInfo.get("posId"));
                userInfo = new SaUserInformationEntity();
                userInfo.setId(UUIDUtils.generateShortUuid());
                userInfo.setPhone(phone);
                userInfo.setName(String.valueOf(crmUserInfo.get("name")));
                userInfo.setRegisterTime(new Date());
                userInfo.setAvatar(avatar);
                userInfo.setRowId(String.valueOf(crmUserInfo.get("rowId")));
                userInfo.setPositionId(posId);
                userInfo.setEmpId(String.valueOf(crmUserInfo.get("empId")));
                if (saUserInfoService.insertSelective(userInfo) <= 0) {
                    LOGGER.info("User info insert failed.");
                    return null;
                }
                SaUserTimeRecodeEntity saUserTimeRecodeEntity = new SaUserTimeRecodeEntity();
                saUserTimeRecodeEntity.setId(UUIDUtils.generateUuid());
                saUserTimeRecodeEntity.setPositionId(posId);
                saUserTimeRecodeEntity.setPhone(phone);
                saUserTimeRecodeEntity.setRowId(String.valueOf(crmUserInfo.get("rowId")));
                saUserTimeRecodeEntity.setLastLogin(new Date());
                saUserTimeRecodeService.insertSelective(saUserTimeRecodeEntity);
                GkEmployeeEntity gkEmployeeEntity = gkEmployeeMapper.selectByPrimaryKey(userInfo.getEmpId());
                gkEmployeeEntity.setPhotoUrl(paramsConfig.getPhotoPrefix() + userInfo.getAvatar());
                gkEmployeeEntity.setUpdatedTime(new Date());
                gkEmployeeMapper.updateByPrimaryKeySelective(gkEmployeeEntity);
            } else {
                // 用户不是第一次登陆，查找该用户是否仍在CRM中为“外部用户”，用以判断该用户是否在职非外部用户或已离职用户
                if (!saUserInfoService.isOuterUserInCrmByPhone(userInfo.getPhone())) {
                    return null;
                }

                crmUserInfo = saUserInfoService.selectCrmUserInfoByPhone(phone);
                String posId = String.valueOf(crmUserInfo.get("posId"));
                if (!(userInfo.getRowId().equals(String.valueOf(crmUserInfo.get("rowId"))))) {
                    userInfo.setRowId(String.valueOf(crmUserInfo.get("rowId")));
                }
                if (!(userInfo.getPositionId().equals(posId))) {
                    userInfo.setPositionId(String.valueOf(posId));
                }
                if (!(userInfo.getPhone().equals(phone))) {
                    userInfo.setPhone(phone);
                }
                if (!(userInfo.getEmpId().equals(String.valueOf(crmUserInfo.get("empId"))))) {
                    userInfo.setEmpId(String.valueOf(crmUserInfo.get("empId")));
                }
                // 更新头像
                userInfo.setAvatar(avatar);
                userInfo.setUpdateTime(new Date());
                saUserInfoService.updateByPrimaryKeySelective(userInfo);
            }
            SaUserTimeRecodeEntity saUserTimeRecodeEntity =
                    saUserTimeRecodeService.selectByPhone(userInfo.getPhone());
            if (!userInfo.getRowId().equals(String.valueOf(crmUserInfo.get("rowId")))) {
                saUserTimeRecodeEntity.setRowId(String.valueOf(crmUserInfo.get("rowId")));
            }
            saUserTimeRecodeEntity.setLastLogin(new Date());
            saUserTimeRecodeEntity.setPositionId(String.valueOf(crmUserInfo.get("posId")));
            saUserTimeRecodeEntity.setPhone(phone);
            saUserTimeRecodeService.updateByPrimaryKeySelective(saUserTimeRecodeEntity);
            Map<String, String> position = saUserInfoService.selectUserPositionInformation(userInfo.getRowId());
            position.put("positionId", userInfo.getPositionId());
            result.put("name", userInfo.getName());
            result.put("status", userInfo.getStatus());
            result.put("phone", userInfo.getPhone());
            result.put("avatar", userInfo.getAvatar());
            result.put("rowId", userInfo.getRowId());
            result.put("position", position);
            result.put("empId", userInfo.getEmpId());
        }
        return result;
    }

    /**
     * 根据用户的员工ID获取该用户在个人设置界面中的基本信息
     *
     * @param empId 用户的员工ID
     * @return 返回该员工ID对应的设置页所需的信息
     */
    @Override
    public Map<String, Object> getUserSettingsInfoByEmpId(String empId) {
        return saUserInfoService.getUserSettingsInfoByEmpId(empId);
    }

    /**
     * 通过用户ID和职位ID获取该用户指定日期下的首页数据
     *
     * @param positionId 职位ID
     * @param empId      用户ID
     * @param date       要查询的日期
     * @return 指定日期下该用户该职位的首页数据信息
     */
    @Override
    public Map<String, Object> getHomeDataByPositionIdAndEmpIdAndDate(String positionId, String empId, String date) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> dailyResult = new HashMap<>();
        dailyResult.put("commentAndLikes", dailyBiz.dailyMessageToday(positionId, empId));
        dailyResult.put("isCommitted", dailyBiz.isCommittedByPositionIdAndEmpIdAndDailyDate(positionId, empId, date));
        Map<String, Object> visitResult = new HashMap<>();
        visitResult.put("nextShopName", gkVisitEntityMapper
                .selectNextNeedVisitShopNameByPositionIdAndEmpIdAndDate(positionId, empId, date));
        visitResult.put("totalVisitCount", gkVisitEntityMapper
                .selectTodayTotalVisitMissionCountByPositionIdAndEmpIdAndDate(positionId, empId, 0, date));
        visitResult.put("nonAccountCount", gkVisitEntityMapper
                .selectTodayTotalVisitMissionCountByPositionIdAndEmpIdAndDate(positionId, empId, 1, date));
        visitResult.put("finishedVisitCount", gkVisitEntityMapper
                .selectTodayTotalVisitMissionCountByPositionIdAndEmpIdAndDate(positionId, empId, 2, date));
        result.put("daily", dailyResult);
        result.put("visit", visitResult);

        //销量提报
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //获取上个月的年月
        String lastMonth = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH);
        String month = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1);

        Map<String, Object> saleResult = new HashMap<>();
        saleResult.put("salesReport",
                saleReportBiz.getSaleReportLists(
                        lastMonth, "已提报", null, positionId, empId, 0, 0).getTotal());
        saleResult.put("salesAll",
                saleReportBiz.getSaleReportLists(
                        lastMonth, null, null, positionId, empId, 0, 0).getTotal());
        result.put("salesReport", saleResult);

        //GT费用核销 本月待处理，及所有
        Map<String, Object> gtCost = new HashMap<>();
        gtCost.put("salesDeal", cxGtReimEntityMapper.selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(
                month, null, positionId, empId, null, null).size());
        gtCost.put("salesAll", cxGtReimEntityMapper.selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(
                month, null, positionId, empId, "queryAll", null).size());
        result.put("gtCost", gtCost);
        //当前 所有的 新建和 驳回
        gtCost.put("salesNew", cxGtReimEntityMapper.selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(
                null, "新建", positionId, empId, null, null).size());
        gtCost.put("salesReject", cxGtReimEntityMapper.selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(
                null, "驳回", positionId, empId, null, null).size());


        return result;
    }

    /**
     * 通过用户的手机号更新该用户的头像
     *
     * @param serverId 头像的微信临时资源路径
     * @param phone    用户的手机号
     * @return 返回用户新头像的七牛云外链地址
     */
    @Override
    public Map<String, String> updateAvatar(String serverId, String phone) {
        Map<String, String> result = new HashMap<>();
        int count;
        String qnKey = String.valueOf(generalFeignService.upLoadToQiNiuYun(serverId).getData());
        if (StringUtils.isEmpty(qnKey) || "null".equals(qnKey)) {
            return null;
        }
        qnKey = paramsConfig.getPhotoPrefix() + qnKey;
        SaUserInformationEntity userInformationEntity =
                saUserInfoService.getUserInformationByPhone(phone);
        userInformationEntity.setAvatar(qnKey);
        userInformationEntity.setUpdateTime(new Date());
        count = saUserInfoService.updateByPrimaryKeySelective(userInformationEntity);
        if (count <= 0) {
            LOGGER.info("Update sales aux user information failed.");
            return null;
        }
        GkEmployeeEntity gkEmployeeEntity = gkEmployeeMapper.selectByPrimaryKey(userInformationEntity.getEmpId());
        gkEmployeeEntity.setPhotoUrl(qnKey);
        gkEmployeeEntity.setUpdatedTime(new Date());
        count = gkEmployeeMapper.updateByPrimaryKeySelective(gkEmployeeEntity);
        if (count <= 0) {
            LOGGER.info("Update gk employee user information failed.");
            return null;
        }
        result.put("avatar", qnKey);
        return result;
    }
}
