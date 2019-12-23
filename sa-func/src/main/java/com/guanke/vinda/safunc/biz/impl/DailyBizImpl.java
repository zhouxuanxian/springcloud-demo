package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.DailyBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.safunc.mapper.*;
import com.guanke.vinda.samodels.model.entity.*;
import com.guanke.vinda.samodels.model.pojo.daily.DailySubmitPojo;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Nicemorning
 */
@Service
public class DailyBizImpl implements DailyBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyBizImpl.class);

    private final ParamsConfig paramsConfig;

    private final GkDailyMapper gkDailyMapper;

    private final GkDataEmpMapper gkDataEmpMapper;

    private final GkDailyPhotoMapper gkDailyPhotoMapper;

    private final SaUserTimeRecodeMapper saUserTimeRecodeMapper;

    private final GeneralFeignService generalFeignService;

    private final GkVisitEntityMapper gkVisitEntityMapper;

    private final GkAccountChangeMapper gkAccountChangeMapper;

    private final GkCompetitorMapper gkCompetitorMapper;

    private final GkExpenseEntityMapper gkExpenseEntityMapper;

    private final GkMissionShopEntityMapper gkMissionShopEntityMapper;

    public DailyBizImpl(GkDailyMapper gkDailyMapper,
                        GkDataEmpMapper gkDataEmpMapper,
                        GkDailyPhotoMapper gkDailyPhotoMapper,
                        SaUserTimeRecodeMapper saUserTimeRecodeMapper,
                        ParamsConfig paramsConfig,
                        GeneralFeignService generalFeignService,
                        GkVisitEntityMapper gkVisitEntityMapper,
                        GkCompetitorMapper gkCompetitorMapper,
                        GkAccountChangeMapper gkAccountChangeMapper,
                        GkExpenseEntityMapper gkExpenseEntityMapper,
                        GkMissionShopEntityMapper gkMissionShopEntityMapper) {
        this.gkDailyMapper = gkDailyMapper;
        this.gkDataEmpMapper = gkDataEmpMapper;
        this.gkDailyPhotoMapper = gkDailyPhotoMapper;
        this.saUserTimeRecodeMapper = saUserTimeRecodeMapper;
        this.paramsConfig = paramsConfig;
        this.generalFeignService = generalFeignService;
        this.gkVisitEntityMapper = gkVisitEntityMapper;
        this.gkCompetitorMapper = gkCompetitorMapper;
        this.gkAccountChangeMapper = gkAccountChangeMapper;
        this.gkExpenseEntityMapper = gkExpenseEntityMapper;
        this.gkMissionShopEntityMapper = gkMissionShopEntityMapper;
    }

    /**
     * 通过职位ID，员工ID和筛选条件获取该员工名下的所有日报，返回分页结果
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param pageNum    页码
     * @param pageSize   页容量
     * @return 符合条件的所有日报的分页结果
     */
    @Override
    public Page<Map<String, Object>> getMyDailys(String positionId, String empId, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(
                () -> gkDailyMapper.selectByPositionAndEmpIdSortByUpdateTime(positionId, empId));
    }

    /**
     * 通过职位ID，员工ID获取该用户最新的日报消息
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回最新的点赞数和评论数
     */
    @Override
    public Map<String, Integer> dailyMessage(String positionId, String empId) {
        Map<String, Integer> result =
                gkDailyMapper.selectDailyCommentAndLikesCountByPositionIdAndEmpIdAfterDate(positionId, empId);
        return result == null ? new HashMap<>() : result;
    }

    /**
     * 通过职位ID，员工ID获取该用户今日的日报消息
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回今日的点赞数和评论数
     */
    @Override
    public Map<String, Integer> dailyMessageToday(String positionId, String empId) {
        Map<String, Integer> result =
                gkDailyMapper.selectDailyCommentAndLikesCountDailyByPositionIdAndEmpId(positionId, empId);
        return result == null ? new HashMap<>() : result;
    }

    /**
     * 保存日报
     *
     * @param option     操作类型，如果为0则保存为草稿，如果为1则为提交日报，如果为2则为提交草稿
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param daily      日报内容
     * @return 返回日报实体
     */
    @Override
    public GkDailyEntity saveDaily(int option, String empId, String positionId, DailySubmitPojo daily) {
        String nullString = "null";
        String id = daily.getId();
        List<GkDailyPhotoEntity> photos = daily.getPhotos();
        GkDailyEntity d;
        if (StringUtils.isEmpty(id) || nullString.equals(id)) {
            d = new GkDailyEntity();
            d.setId(UUIDUtils.generateUuid());
            Date date = new Date();
            d.setReportDate(date);
            //日报创建日期（日报日期）
            d.setDailyDate(date);
            d.setEmpId(empId);
            d.setPositionId(positionId);
        } else {
            d = gkDailyMapper.selectByPrimaryKey(id);
            d.setUpdatedTime(new Date());
            d.setVersion(d.getVersion() + 1);
        }

        if (photos != null && photos.size() > 0) {
            photos.forEach(item -> {
                GkDailyPhotoEntity gkDailyPhotoEntity = new GkDailyPhotoEntity();
                gkDailyPhotoEntity.setDailyId(item.getDailyId());
                gkDailyPhotoEntity.setPhotoKey(item.getPhotoKey());
                if (gkDailyPhotoMapper.insertSelective(gkDailyPhotoEntity) <= 0) {
                    LOGGER.info("Save daily photo failed.");
                }
            });
        } else if (option != 0) {
            LOGGER.info("Daily must contain photos but it does`n.");
            return null;
        }

        String content = daily.getContent();
        String coreWork = daily.getCoreWork();
        if (option != 0) {
            if (content == null || content.isEmpty() || nullString.equals(content)) {
                LOGGER.info("Content should not be empty");
                return null;
            }
        }
        if (option != 0) {
            if (coreWork == null || coreWork.isEmpty() || nullString.equals(coreWork)) {
                LOGGER.info("Core work should not be empty");
                return null;
            }
        }
        d.setContent(content);
        d.setCoreWork(coreWork);
        //提交时间（时分秒）
        if (option != 2) {
            if (id == null || id.isEmpty() || nullString.equals(id)) {
                d.setReportDate(new Date());
            }
        }
        //记录状态
        if (option == 0) {
            d.setStatus("SA_COMMIT_DRAFT");
        } else {
            d.setStatus("SA_COMMIT");
        }

        if (option != 2) {
            if (id == null || id.isEmpty() || nullString.equals(id)) {
                gkDailyMapper.insertSelective(d);
            } else {
                gkDailyMapper.updateByPrimaryKeySelective(d);
            }
        } else {
            gkDailyMapper.updateByPrimaryKeySelective(d);
        }

        List<GkDataEmpEntity> dataEmpEntities =
                gkDataEmpMapper.selectByPositionIdAndRecordDate(positionId, d.getDailyDate());

        GkDataEmpEntity empData =
                dataEmpEntities.size() != 0 ? dataEmpEntities.get(0) : new GkDataEmpEntity();
        if (dataEmpEntities.size() == 0) {
            //记录日报创建次数
            empData.setId(UUIDUtils.generateUuid().substring(7, 22));
            empData.setDailyCreatedCnt(1);
            empData.setPositionId(positionId);
            Date date = new Date();
            empData.setRecordDate(date);
            empData.setMonth(LocalDate.now().getMonthValue());
            gkDataEmpMapper.insertSelective(empData);
        } else {
            //记录日报提交次数(查询并汇总日报创建日期的日报提交次数，而不一定为点击该按钮的日期)
            dataEmpEntities.get(0).setDailyCnt(dataEmpEntities.get(0).getDailyCnt() + 1);
            gkDataEmpMapper.updateByPrimaryKeySelective(dataEmpEntities.get(0));
        }

        //处理图片
        GkDailyPhotoEntity newPhoto;
        for (
                GkDailyPhotoEntity dp : photos) {
            newPhoto = new GkDailyPhotoEntity();
            newPhoto.setDailyId(d.getId());
            newPhoto.setPhotoKey(dp.getPhotoKey());
            gkDailyPhotoMapper.insertSelective(newPhoto);
        }
        return d;
    }

    /**
     * 根据日报的ID获取该日报详情
     *
     * @param id 日报ID
     * @return 返回日报实体
     */
    @Override
    public Map<String, Object> getDailyDetail(String id) {
        Map<String, Object> result = new HashMap<>();
        GkDailyEntity gkDailyEntity = gkDailyMapper.selectByPrimaryKey(id);
        List<GkDailyPhotoEntity> photos = new ArrayList<>();
        gkDailyPhotoMapper.selectByDailyId(id).forEach(item -> {
            item.setPhotoKey(paramsConfig.getPhotoPrefix() + item.getPhotoKey());
            photos.add(item);
        });
        result.put("photo", photos);
        result.put("daily", gkDailyEntity);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
        String dateStr = dateFormat.format(gkDailyEntity.getReportDate());
        result.put("visitHistory", this.getVisitMissionHistoryListByConditionForDaily(gkDailyEntity.getEmpId(),
                gkDailyEntity.getPositionId(), dateStr));
        result.put("comment", gkDailyMapper.selectCommentByDailyId(id));
        return result;
    }

    /**
     * 通过当前用户的职位ID，员工ID和当前日期判断该用户所处职位下今天是否已经提交过日报
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param dailyDate  当日日期
     * @return 如果已经提交过，则返回true；反之返回false
     */
    @Override
    public boolean isCommittedByPositionIdAndEmpIdAndDailyDate(String positionId, String empId, String dailyDate) {
        return gkDailyMapper.selectCountByPositionIdAndEmpIdAndDraftDate(positionId, empId, dailyDate) > 0;
    }

    /**
     * 通过当前用户的职位ID，员工ID和当前日期判断该用户所处职位下今天是否已经保存过日报草稿
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param dailyDate  当日日期
     * @return 如果已经保存过草稿，则返回该草稿的ID；反之返回null
     */
    @Override
    public String getDraftIdByPositionIdAndEmpIdAndDailyDate(String positionId, String empId, String dailyDate) {
        return gkDailyMapper.selectDraftIdByPositionIdAndEmpIdAndDraftDate(positionId, empId, dailyDate);
    }

    /**
     * 根据手机号更新该用户的日报消息记录查看时间
     *
     * @param phone 用户的手机号
     */
    @Override
    public int updateMessageCheck(String phone) {
        SaUserTimeRecodeEntity saUserTimeRecodeEntity = saUserTimeRecodeMapper.selectByPhone(phone);
        saUserTimeRecodeEntity.setDailyMessageCheck(new Date());
        return saUserTimeRecodeMapper.updateByPrimaryKeySelective(saUserTimeRecodeEntity);
    }

    /**
     * 通过员工ID和职位ID获取该员工的所有日报消息提醒列表
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回该员工ID和职位ID下的所有日报消息提醒
     */
    @Override
    public List<Map<String, Object>> getDailyMessageByPositionIdAndEmpId(String positionId, String empId) {
        List<Map<String, Object>> mapList = gkDailyMapper.selectalldailycommentmessagebypositionidandempId(positionId, empId);
        mapList.addAll(gkDailyMapper.selectAllDailyLikeMessageByPositionIdAndEmpId(positionId, empId));
        mapList.sort(Comparator.comparing(m -> String.valueOf(m.get("time"))));
        return mapList;
    }

    /**
     * 通过日报ID获取该日报下的图片
     *
     * @param id 日报ID
     * @return 返回该日报下的所有图片
     */
    @Override
    public List<String> getDailyImages(String id) {
        List<String> result = new ArrayList<>();
        List<GkDailyPhotoEntity> gkDailyPhotoEntities = gkDailyPhotoMapper.selectByDailyId(id);
        gkDailyPhotoEntities.forEach(item -> result.add(id));
        return result;
    }

    /**
     * 通过员工ID和职位ID获取日报提交时顶部的初始数据
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       要查询的日期
     * @return 返回符合条件的日报提交初始数据
     */
    @Override
    public Map<String, Object> getVisitMissionForDaily(String empId, String positionId, String date) {
        Map<String, Object> result = new HashMap<>();
        result.put("visitHistory", this.getVisitMissionHistoryListByConditionForDaily(empId, positionId, date));
        Integer competitorCount = gkCompetitorMapper.selectCountByDate(positionId, empId, date);
        result.put("competitor", competitorCount);
        result.put("photos", this.getUploadPhotosCount(empId, positionId, date));
        Integer shopCount = gkAccountChangeMapper.selectCountByEmpIdAndDate(empId, date);
        result.put("shop", shopCount);
        return result;
    }

    /**
     * 统计今日上传的图片数
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       查询日期
     * @return 返回该员工在该职位下今日上传的照片数
     */
    private int getUploadPhotosCount(String empId, String positionId, String date) {
        return gkVisitEntityMapper.
                selectVisitPhotosListByPositionIdAndEmpIdAndDate(positionId, empId, date).size();
    }

    /**
     * 根据员工ID和职位ID查询该员工在当天的拜访记录，仅用于工作日报提交中的展示
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       要查询的日期
     * @return 返回符合条件的拜访记录
     */
    private List<Map<String, Object>> getVisitMissionHistoryListByConditionForDaily(String empId, String positionId, String date) {
        return gkVisitEntityMapper.getVisitMissionHistoryListByConditionForDaily(empId, positionId, date);
    }

    /**
     * 通过员工ID、职位ID和日期查询指定日期的竞品记录
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param date       指定日期
     * @return 返回符合条件的竞品记录
     */
    @Override
    public List<Map<String, Object>> getCompetitorListByPositionIdAndEmpIdAndDate(String positionId, String empId, String date) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> queryResult =
                gkCompetitorMapper.selectCompetitorListByPositionIdAndEmpIdAndDate(positionId, empId, date);
        List<String> names = new ArrayList<>();
        queryResult.forEach(item -> {
            String shopName = String.valueOf(item.get("accountName"));
            List<Map<String, Object>> res = new ArrayList<>();
            Map<String, Object> tempObject = new HashMap<>();
            if (!names.contains(shopName)) {
                tempObject.put("name", shopName);
                names.add(shopName);
                queryResult.forEach(element -> {
                    if (shopName.equals(String.valueOf(element.get("accountName")))) {
                        element.put("prefix", paramsConfig.getPhotoPrefix());
                        res.add(element);
                    }
                });
                tempObject.put("competitors", res);
                result.add(tempObject);
            }
        });
        return result;
    }

    /**
     * 通过员工ID、职位ID和日期查询指定日期的拍照记录
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       指定日期
     * @return 返回符合条件的拍照记录
     */
    @Override
    public List<Map<String, Object>> getUploadPhotosListByEmpIdAndPositionIdAndDate(String empId, String positionId, String date) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> queryResult = new ArrayList<>();
        // 拜访图片
        queryResult.addAll(gkVisitEntityMapper.
                selectVisitPhotosListByPositionIdAndEmpIdAndDate(positionId, empId, date));
        List<String> names = new ArrayList<>();
        queryResult.forEach(item -> {
            String shopName = String.valueOf(item.get("accountName"));
            List<Map<String, Object>> res = new ArrayList<>();
            Map<String, Object> tempObject = new HashMap<>();
            if (!names.contains(shopName)) {
                tempObject.put("accountName", shopName);
                names.add(shopName);
                queryResult.forEach(element -> {
                    if (shopName.equals(String.valueOf(element.get("accountName")))) {
                        element.put("prefix", paramsConfig.getPhotoPrefix());
                        res.add(element);
                    }
                });
                tempObject.put("shop", res);
                result.add(tempObject);
            }
        });
        return result;
    }

    /**
     * 通过员工ID和日期查询指定日期的门店维护记录
     *
     * @param empId 员工ID
     * @param date  查询日期
     * @return 返回符合条件的门店维护记录
     */
    @Override
    public List<Map<String, Object>> getMaintainAccountListByEmpIdAndDate(String empId, String date) {
        return gkVisitEntityMapper.selectMaintainAccountByEmpIdAndDate(empId, date);
    }
}
