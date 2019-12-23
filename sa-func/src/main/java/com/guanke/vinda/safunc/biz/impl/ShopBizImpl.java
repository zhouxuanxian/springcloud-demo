package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.ShopBiz;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.safunc.mapper.GkAccountChangeMapper;
import com.guanke.vinda.safunc.mapper.GkAccountEntityMapper;
import com.guanke.vinda.safunc.mapper.SaUserInfoMapper;
import com.guanke.vinda.samodels.model.pojo.shop.ShopChangePojo;
import com.guanke.vinda.samodels.model.pojo.shop.ShopManageListPojo;
import com.guanke.vinda.samodels.model.pojo.shop.create.NewShopSubmitPojo;
import com.guanke.vinda.samodels.model.pojo.shop.edit.ShopChangeSubmitPojo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店相关业务逻辑接口
 *
 * @author Nicemorning
 */
@Service
public class ShopBizImpl implements ShopBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopBizImpl.class);

    private final GkAccountEntityMapper gkAccountEntityMapper;

    private final SaUserInfoMapper saUserInfoMapper;

    private final GkAccountChangeMapper gkAccountChangeMapper;

    private final GeneralFeignService generalFeignService;

    public ShopBizImpl(GkAccountEntityMapper gkAccountEntityMapper,
                       SaUserInfoMapper saUserInfoMapper,
                       GkAccountChangeMapper gkAccountChangeMapper,
                       GeneralFeignService generalFeignService) {
        this.gkAccountEntityMapper = gkAccountEntityMapper;
        this.saUserInfoMapper = saUserInfoMapper;
        this.gkAccountChangeMapper = gkAccountChangeMapper;
        this.generalFeignService = generalFeignService;
    }

    /**
     * 通过当前用户的职位ID，组织ID，微信角色和筛选条件筛选所有的门店列表
     *
     * @param id         职位ID或组织ID
     * @param isPosition 如果是通过职位，则为true，反之则为false以组织查找
     * @param params     筛选条件
     * @return 返回符合条件的但无拜访任务的门店列表
     * @see ShopBiz
     */
    @Override
    public Page<ShopManageListPojo> selectAccountByCondition(
            int page, int pageSize, String id, boolean isPosition, Map<String, Object> params) {
        Page<ShopManageListPojo> result;
        result = PageHelper.startPage(page, pageSize).doSelectPage(() -> gkAccountEntityMapper.
                selectAccountByCondition(
                        id,
                        String.valueOf(params.get("keyword")),
                        String.valueOf(params.get("area")),
                        String.valueOf(params.get("level")),
                        String.valueOf(params.get("feature")),
                        String.valueOf(params.get("city")),
                        LocalDate.now().toString()
                ));
        return result;
    }

    /**
     * 通过门店ID获取门店的详情
     *
     * @param shopId 门店ID
     * @return 返回该门店详情信息
     */
    @Override
    public Map<String, Object> getShopDetailByShopId(String shopId) {
        //查询门店详情
        Map<String, Object> result = gkAccountEntityMapper.selectShopDetailByShopId(shopId);
        if (result != null) {
            //根据门店Id查询最后一次拜访时间时间
            Map<String, Object> lastVisit = gkAccountEntityMapper.selectLastVisitByShopId(shopId);
            //根据门店Id查询最后一次更新库存
            Map<String, Object> lastStock = gkAccountEntityMapper.selectLastStockByShopId(shopId);
            //根据门店Id查询竞品信息
            Map<String, Object> lastCompetitor = gkAccountEntityMapper.selectLastCompetitorByShopId(shopId);
            //根据门店Id查询门店V码数量
            Map<String, Object> countVcode = gkAccountEntityMapper.selectCountVcodeByShopId(shopId);
            //根据门店Id查询销量提高
            Map<String, Object> lastSalesReport = gkAccountEntityMapper.selectLastStockDateByShopId(shopId);

            result.put("lastVisit", lastVisit == null ? "" : lastVisit.get("lastDate"));
            result.put("lastStock", lastStock == null ? "" : lastStock.get("lastDate"));
            result.put("lastCompetitor", lastCompetitor == null ? "" : lastCompetitor.get("lastDate"));
            result.put("countVcode", countVcode == null ? 0 : countVcode.get("countVcode"));
            result.put("lastStockDate", lastSalesReport == null ? "" : lastSalesReport.get("lastDate"));
            result.put("salesReportId", lastSalesReport == null ? "" : lastSalesReport.get("salesReportId"));
        }
        return result;
    }

    /**
     * 通过门店ID获取门店基础信息
     *
     * @param accountId 门店ID
     * @return 返回该门店基础信息
     */
    @Override
    public Map<String, Object> getShopByShopId(String accountId) {
        Map<String, Object> result = gkAccountEntityMapper.selectShopDetailByShopId(accountId);
        return result;
    }

    /**
     * 通过门店ID获取门店的变更历史
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param id       门店ID
     * @return 返回该门店的变更历史
     */
    @Override
    public Page<ShopChangePojo> getShopChangeHistoryById(int page, int pageSize, String id) {
        return PageHelper.startPage(page, pageSize).doSelectPage(() ->
                gkAccountEntityMapper.selectShopChangeHistoryByShopId(id)
        );
    }

    /**
     * 通过门店ID获取门店变更历史时间，仅用于门店详情页展示
     *
     * @param id 门店ID
     * @return 返回该门店的变更历史时间
     */
    @Override
    public Map<String, String> getShopChangeTimeById(String id) {
        String time = gkAccountEntityMapper.selectShopChangeTimeByShopId(id);
        Map<String, String> result = new HashMap<>();
        if (time != null) {
            result.put("date", time.split("\\|")[0]);
            result.put("time", time.split("\\|")[1]);
        }
        return result;
    }

    /**
     * 根据职位ID获取于其相同职位的人，用于客户团队选择
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param id       要查找的职位ID
     * @param name     要查找的人员姓名
     * @return 返回与该职位ID相同的其他人员
     */
    @Override
    public Page<Map<String, String>> getEmpListByPositionId(int page, int pageSize, String id, String name) {
        return PageHelper.startPage(page, pageSize).doSelectPage(() ->
                gkAccountEntityMapper.selectEmpListByPositionId(id, name));
    }

    /**
     * 通过用户的职位ID，获取该用户名下所有门店的行政区域列表
     *
     * @param positionId 职位ID
     * @return 返回该职位ID下所有的门店行政区域列表
     */
    @Override
    public List<String> getAllShopDistrictByPositionId(String positionId) {
        return gkAccountEntityMapper.selectAllShopDistrictByPositionId(positionId);
    }

    /**
     * 通过职位ID获取该职位下所属门店可选的经销商列表
     *
     * @param positionId 职位ID
     * @param keyWord    搜索关键字
     * @param page       页码
     * @param pageSize   页容量
     * @return 该职位下所有门店的可选经销商列表
     */
    @Override
    public Page<Map<String, String>> getAllDealerListByPositionId(String positionId, String keyWord, int page, int pageSize) {
        return PageHelper.startPage(page, pageSize).doSelectPage(() ->
                gkAccountEntityMapper.selectAllDealerListByPositionId(positionId, keyWord));
    }

    /**
     * 新建门店
     *
     * @param phone      用户手机号
     * @param postnId    职位ROW-ID
     * @param orgId      组织ID
     * @param submitPojo 门店参数
     * @return 如果新建成功则返回"success"，反之返回相应错误信息
     */
    @Override
    public String newShop(String phone, String postnId, String orgId, NewShopSubmitPojo submitPojo) {
        Map<String, Object> request = new HashMap<>();
        // 记录用户工号，经销宝中工号为手机号
        if (this.isNullOrEmptyString(phone)) {
            LOGGER.info("Invalid user phone.");
            return "缺失用户工号";
        }
        // 设置组织关系
        request.put("attr27", orgId);
        request.put("attr28", postnId);

        // Default value
        request.put("attr01", phone);
        request.put("attr02", "Y");
        request.put("attr03", "现通");
        request.put("attr04", "A");
        request.put("attr05", "GT终端");
        request.put("attr13", "经销宝");
        request.put("attr14", "N");

        // 设置地址信息
        if (this.isNullOrEmptyString(submitPojo.getAddress().getProvince())) {
            LOGGER.info("Invalid address province info");
            return "缺失省份信息";
        }
        request.put("attr06", submitPojo.getAddress().getProvince());
        if (this.isNullOrEmptyString(submitPojo.getAddress().getCity())) {
            LOGGER.info("Invalid address city info");
            return "缺失地级市信息";
        }
        request.put("attr07", submitPojo.getAddress().getCity());
        if (this.isNullOrEmptyString(submitPojo.getAddress().getDistrict())) {
            LOGGER.info("Invalid address district info");
            return "缺失区/县信息";
        }
        request.put("attr08", submitPojo.getAddress().getDistrict());
        if (this.isNullOrEmptyString(submitPojo.getAddress().getAddress())) {
            LOGGER.info("Invalid address location info");
            return "缺失精确位置信息";
        }
        request.put("attr22", submitPojo.getAddress().getAddress());

        // 设置门店基础信息
        if (this.isNullOrEmptyString(submitPojo.getAccountName())) {
            LOGGER.info("Invalid account name info");
            return "缺失门店名称";
        }
        request.put("attr09", submitPojo.getAccountName());
        if (this.isNullOrEmptyString(submitPojo.getDealerId())) {
            LOGGER.info("Invalid account dealer info");
            return "缺失经销商信息";
        }
        request.put("attr25", submitPojo.getDealerId());
        if (this.isNullOrEmptyString(submitPojo.getCustomerTeamId())) {
            LOGGER.info("Invalid account customer team info");
            return "缺失客户团队信息";
        }
        if (this.isNullOrEmptyString(submitPojo.getAccountLevel())) {
            LOGGER.info("Invalid account level info");
            return "缺失门店等级信息";
        }
        request.put("attr18", submitPojo.getAccountLevel());
        if (this.isNullOrEmptyString(submitPojo.getAccountArea())) {
            LOGGER.info("Invalid address location info");
            return "缺失门店面积信息";
        }
        request.put("attr24", submitPojo.getAccountArea());
        request.put("attr12", submitPojo.getAccountResource().getEndFrameCount());
        request.put("attr16", submitPojo.getAccountResource().getShelfCount());
        request.put("attr17", submitPojo.getAccountResource().getBlockCount());
        request.put("attr19", submitPojo.getAccountFeature());

        // 设置联系人信息
        if (this.isNullOrEmptyString(submitPojo.getContactName())) {
            LOGGER.info("Invalid contact name info");
            return "缺失联系人姓名";
        }
        request.put("attr11", submitPojo.getContactName());
        request.put("attr26", submitPojo.getContactPhone());

        // 设置维达属性
        if (this.isNullOrEmptyString(submitPojo.getPaperVolume())) {
            LOGGER.info("Invalid paper volume info");
            return "缺失纸品容量信息";
        }
        request.put("attr23", submitPojo.getPaperVolume());
        if (this.isNullOrEmptyString(submitPojo.getVindaSales())) {
            LOGGER.info("Invalid vinda sales info");
            return "缺失维达销量信息";
        }
        request.put("attr10", submitPojo.getVindaSales());
        request.put("attr15", submitPojo.getVindaRank());

        // 发送请求到CRM
        if (generalFeignService.saveShopInfo(request).getCode() != 200) {
            LOGGER.info("Request to crm failed. Check log in sa-sync module");
            return "请求CRM失败";
        }
        return "success";
    }

    /**
     * 判断字符串是否为null或者空或者"null"
     *
     * @param string 要判断的字符串
     * @return 如果字符串有内容则返回false，反之true
     */
    private boolean isNullOrEmptyString(String string) {
        String nullStr = "null";
        return string == null || StringUtils.isEmpty(string) || nullStr.equals(string);
    }

    /**
     * 修改门店信息
     *
     * @param submitPojo 门店参数
     * @return 如果修改成功则返回"success"，反之返回相应错误信息
     */
    @Override
    public String editShop(ShopChangeSubmitPojo submitPojo) {
        Map<String, Object> request = new HashMap<>();
        if (this.isNullOrEmptyString(submitPojo.getAccountId())) {
            LOGGER.info("Missing account id");
            return "缺失门店ID";
        }
        request.put("attr01", submitPojo.getAccountId());

        if (this.isNullOrEmptyString(submitPojo.getAccountName())) {
            LOGGER.info("Missing account name");
            return "缺失门店名称";
        }
        request.put("attr02", submitPojo.getAccountName());

        if (this.isNullOrEmptyString(submitPojo.getAddressPojo().getAddress())) {
            LOGGER.info("Missing account detail address");
            return "缺失门店详细地址";
        }
        request.put("attr12", submitPojo.getAddressPojo().getAddress());

        if (this.isNullOrEmptyString(submitPojo.getAddressPojo().getProvince())) {
            LOGGER.info("Missing account province");
            return "缺失门店省份信息";
        }
        request.put("attr09", submitPojo.getAddressPojo().getProvince());

        if (this.isNullOrEmptyString(submitPojo.getAddressPojo().getCity())) {
            LOGGER.info("Missing account city");
            return "缺失门店地级市信息";
        }
        request.put("attr10", submitPojo.getAddressPojo().getCity());

        if (this.isNullOrEmptyString(submitPojo.getAddressPojo().getDistrict())) {
            LOGGER.info("Missing account district");
            return "缺失门店区/县信息";
        }
        request.put("attr11", submitPojo.getAddressPojo().getDistrict());

        if (this.isNullOrEmptyString(submitPojo.getDealerId())) {
            LOGGER.info("Missing account dealer id");
            return "缺失门店经销商信息";
        }
        request.put("attr03", submitPojo.getDealerId());

        if (this.isNullOrEmptyString(submitPojo.getSecondType())) {
            LOGGER.info("Missing account second type");
            return "缺失门店二级分类";
        }
        request.put("attr06", submitPojo.getSecondType());

        if (this.isNullOrEmptyString(submitPojo.getThirdType())) {
            LOGGER.info("Missing account third type");
            return "缺失门店三级分类";
        }
        request.put("attr07", submitPojo.getThirdType());

        if (this.isNullOrEmptyString(submitPojo.getAccountLevel())) {
            LOGGER.info("Missing account level");
            return "缺失门店等级";
        }
        request.put("attr04", submitPojo.getAccountLevel());

        if (this.isNullOrEmptyString(submitPojo.getAccountFeature())) {
            LOGGER.info("Missing account feature");
            return "缺失门店特性";
        }
        request.put("attr14", submitPojo.getAccountFeature());

        if (this.isNullOrEmptyString(submitPojo.getAccountArea())) {
            LOGGER.info("Missing account area");
            return "缺失门店面积";
        }
        request.put("attr17", submitPojo.getAccountArea());

        if (this.isNullOrEmptyString(submitPojo.getContactName())) {
            LOGGER.info("Missing account contact name");
            return "缺失门店联系人姓名";
        }
        request.put("attr13", submitPojo.getContactName());

        if (this.isNullOrEmptyString(submitPojo.getCoverAccount())) {
            LOGGER.info("Missing account is cover by business");
            return "缺失业务是否覆盖门店信息";
        }
        request.put("attr08", submitPojo.getCoverAccount());

        if (this.isNullOrEmptyString(submitPojo.getAccountStatus())) {
            LOGGER.info("Missing account status");
            return "缺失门店状态";
        }
        request.put("attr05", submitPojo.getAccountStatus());
        request.put("attr15", submitPojo.getContactPhone());
        request.put("attr16", submitPojo.getVindaRank());

        // 发送请求到CRM
        if (generalFeignService.updateShopInfo(request).getCode() != 200) {
            LOGGER.info("Request to crm failed. Check log in sa-sync module");
            return "请求CRM失败";
        }
        return "success";
    }
}
