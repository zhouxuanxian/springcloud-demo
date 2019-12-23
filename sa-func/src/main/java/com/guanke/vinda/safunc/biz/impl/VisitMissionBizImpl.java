package com.guanke.vinda.safunc.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guanke.vinda.safunc.biz.VisitMissionBiz;
import com.guanke.vinda.safunc.config.ParamsConfig;
import com.guanke.vinda.safunc.feign.GeneralFeignService;
import com.guanke.vinda.safunc.mapper.*;
import com.guanke.vinda.samodels.model.entity.*;
import com.guanke.vinda.samodels.model.enums.PhotoTypeContent;
import com.guanke.vinda.samodels.model.pojo.visit.*;
import com.guanke.vinda.samodels.model.utils.MapUtil;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 门店拜访相关业务实现类
 *
 * @author Nicemorning
 */
@Service
public class VisitMissionBizImpl implements VisitMissionBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitMissionBizImpl.class);

    private static final String NONACCOUNT = "NonAccount";

    private static final String NULLSTR = "null";

    private final ParamsConfig paramsConfig;

    private final GkVisitEntityMapper gkVisitEntityMapper;

    private final GkVisitPhotoEntityMapper gkVisitPhotoEntityMapper;

    private final GkDataEmpMapper gkDataEmpMapper;

    private final GkAccountEntityMapper gkAccountEntityMapper;

    private final GkAccountVcodeEntityMapper gkAccountVcodeEntityMapper;

    private final GeneralFeignService generalFeignService;

    private final SaPhotoConncetEntityMapper saPhotoConncetEntityMapper;

    private final GkCompetitorMapper gkCompetitorMapper;

    private final GkAccountCompetitorEntityMapper gkAccountCompetitorEntityMapper;

    private final GkCompetitorPhotoMapper gkCompetitorPhotoMapper;

    private final GkStockOrderEntityMapper gkStockOrderEntityMapper;

    private final GkListOfValueEntityMapper gkListOfValueEntityMapper;

    private final SaVisitContentEntityMapper saVisitContentEntityMapper;

    private final SaVisitExtEntityMapper saVisitExtEntityMapper;

    private final SaVisitUpdateShopTempEntityMapper saVisitUpdateShopTempEntityMapper;

    private final SaUserInfoMapper saUserInfoMapper;

    private final SaVisitCompetitorPhotoConnectEntityMapper saVisitCompetitorPhotoConnectEntityMapper;

    public VisitMissionBizImpl(GkVisitEntityMapper gkVisitEntityMapper,
                               GkDataEmpMapper gkDataEmpMapper,
                               GkAccountEntityMapper gkAccountEntityMapper,
                               GkAccountVcodeEntityMapper gkAccountVcodeEntityMapper,
                               GeneralFeignService generalFeignService,
                               GkVisitPhotoEntityMapper gkVisitPhotoEntityMapper,
                               ParamsConfig paramsConfig,
                               SaPhotoConncetEntityMapper saPhotoConncetEntityMapper,
                               GkCompetitorMapper gkCompetitorMapper,
                               GkAccountCompetitorEntityMapper gkAccountCompetitorEntityMapper,
                               GkCompetitorPhotoMapper gkCompetitorPhotoMapper,
                               GkStockOrderEntityMapper gkStockOrderEntityMapper,
                               GkListOfValueEntityMapper gkListOfValueEntityMapper,
                               SaVisitContentEntityMapper saVisitContentEntityMapper,
                               SaVisitExtEntityMapper saVisitExtEntityMapper,
                               SaVisitUpdateShopTempEntityMapper saVisitUpdateShopTempEntityMapper,
                               SaUserInfoMapper saUserInfoMapper,
                               SaVisitCompetitorPhotoConnectEntityMapper saVisitCompetitorPhotoConnectEntityMapper) {
        this.gkVisitEntityMapper = gkVisitEntityMapper;
        this.gkDataEmpMapper = gkDataEmpMapper;
        this.gkAccountEntityMapper = gkAccountEntityMapper;
        this.gkAccountVcodeEntityMapper = gkAccountVcodeEntityMapper;
        this.generalFeignService = generalFeignService;
        this.gkVisitPhotoEntityMapper = gkVisitPhotoEntityMapper;
        this.paramsConfig = paramsConfig;
        this.saPhotoConncetEntityMapper = saPhotoConncetEntityMapper;
        this.gkCompetitorMapper = gkCompetitorMapper;
        this.gkAccountCompetitorEntityMapper = gkAccountCompetitorEntityMapper;
        this.gkCompetitorPhotoMapper = gkCompetitorPhotoMapper;
        this.gkStockOrderEntityMapper = gkStockOrderEntityMapper;
        this.gkListOfValueEntityMapper = gkListOfValueEntityMapper;
        this.saVisitContentEntityMapper = saVisitContentEntityMapper;
        this.saVisitExtEntityMapper = saVisitExtEntityMapper;
        this.saVisitUpdateShopTempEntityMapper = saVisitUpdateShopTempEntityMapper;
        this.saUserInfoMapper = saUserInfoMapper;
        this.saVisitCompetitorPhotoConnectEntityMapper = saVisitCompetitorPhotoConnectEntityMapper;
    }

    /**
     * 通过员工的职位ID和员工ID，查询该员工在指定月份下的任务统计数据
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       指定查询日期
     * @return 返回该月份所有的任务统计数据
     */
    @Override
    public List<Map<String, Object>> getMonthVisitMissionByEmpIdAndPositionIdAndMonth(String empId,
                                                                                      String positionId,
                                                                                      String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Map<String, Object>> result =
                gkVisitEntityMapper.selectMonthMissionByEmpIdAndPositionIdAndStartDayAndEndDay(
                        localDate.with(TemporalAdjusters.firstDayOfMonth()).toString(),
                        localDate.with(TemporalAdjusters.lastDayOfMonth()).toString(),
                        empId,
                        positionId
                );
        return result;
    }

    /**
     * 通过员工的职位ID和员工ID，查询该员工指定日期下的所有任务列表
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param date       指定查询日期
     * @param page       页码
     * @param pageSize   页容量
     * @return 返回指定日期下的所有任务列表
     */
    @Override
    public Page<Map<String, Object>> getMissionListByDateAndPositionIdAndEmpId(String positionId, String empId, String date, int page, int pageSize) {
        Page<Map<String, Object>> result = PageHelper.startPage(page, pageSize).doSelectPage(() -> {
            gkVisitEntityMapper.selectMissionListByDateAndPositionIdAndEmpId(empId, positionId, date);
        });
        return result;
    }

    /**
     * 新建拜访事项，如果是非拜访事项，则accountIds传入null即可
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param visitDate  拜访时间
     * @param accountIds 客户ID列表
     * @return 如果新建成功返回true，否则返回false
     */
    @Override
    public boolean insertAccountVisitEntity(String empId, String positionId, Date visitDate, List<String> accountIds) {
        AtomicBoolean flag = new AtomicBoolean(false);
        accountIds.forEach(id -> {
            if (NONACCOUNT.equals(id)) {
                // 非拜访事项
                GkVisitEntity gkVisitEntity = new GkVisitEntity();
                gkVisitEntity.setAccountId(null);
                gkVisitEntity.setVisitType("NonAccount");
                gkVisitEntity.setEmpId(empId);
                gkVisitEntity.setPositionId(positionId);
                //添加拜访任务创建人empId和职位id
                gkVisitEntity.setCreatedVisitEmpId(empId);
                gkVisitEntity.setCreatedVisitPostnId(positionId);
                gkVisitEntity.setStatus("SA_COMMIT");
                gkVisitEntity.setVisitDate(visitDate);
                gkVisitEntity.setLikeQty(0);
                flag.set(gkVisitEntityMapper.insertSelective(gkVisitEntity) > 0);
                LOGGER.info("New params mission none account set.");
            } else {
                // 拜访事项
                GkVisitEntity gkVisitEntity = new GkVisitEntity();
                gkVisitEntity.setAccountId(id);
                gkVisitEntity.setVisitType("RealAccount");
                gkVisitEntity.setEmpId(empId);
                gkVisitEntity.setPositionId(positionId);
                //添加拜访任务创建人empId和职位id
                gkVisitEntity.setCreatedVisitEmpId(empId);
                gkVisitEntity.setCreatedVisitPostnId(positionId);
                gkVisitEntity.setStatus("SA_COMMIT");
                gkVisitEntity.setVisitDate(visitDate);
                gkVisitEntity.setLikeQty(0);
                flag.set(gkVisitEntityMapper.insertSelective(gkVisitEntity) > 0);
                LOGGER.info("New params mission account is :" + id + " was set.");
            }
        });
        // 拜访日志记录
        List<GkDataEmpEntity> empDatas =
                gkDataEmpMapper.selectByPositionIdAndRecordDate(positionId, new Date());
        GkDataEmpEntity gkDataEmpEntity;
        if (empDatas != null && empDatas.size() > 0) {
            gkDataEmpEntity = empDatas.get(0);
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();
            gkDataEmpEntity = new GkDataEmpEntity();
            gkDataEmpEntity.setPositionId(positionId);
            gkDataEmpEntity.setRecordDate(new Date());
            gkDataEmpEntity.setYear(localDateTime.getYear());
            gkDataEmpEntity.setMonth(localDateTime.getMonthValue());
            gkDataEmpEntity.setLoginFlag(1);
            gkDataEmpMapper.insertSelective(gkDataEmpEntity);
        }
        int visitCreatedCnt = gkDataEmpEntity.getVisitCreatedCnt() == null ? 0 : gkDataEmpEntity.getVisitCreatedCnt();
        gkDataEmpEntity.setVisitCreatedCnt(visitCreatedCnt + 1);
        if (gkDataEmpEntity.getVisitCreatedCnt() == null) {
            gkDataEmpMapper.insertSelective(gkDataEmpEntity);
        } else {
            gkDataEmpMapper.updateByPrimaryKeySelective(gkDataEmpEntity);
        }
        return flag.get();
    }

    @Override
    public Map<String, Object> getVisitByVisitIdAndEmpId(String visitId, String empId) {
        Map<String, Object> result = gkVisitEntityMapper.selectVisitByIdAndEmpId(visitId);
        // 拜访目的
        String purpose = (String) result.get("purpose");
        List<String> purposeValues = new ArrayList<>();
        List<String> purposeCodes = new ArrayList<>();
        String[] purposes = StringUtils.split(purpose, ",");
        if (purposes != null && purposes.length > 0) {
            for (String code : purposes) {
                Map<String, Object> lovValue = gkVisitEntityMapper.selectVisitPuroseValueByCode(code);
                purposeCodes.add(code);
                purposeValues.add((String) lovValue.get("purpose_value"));
            }
            result.put("purposeCodes", purposeCodes);
            result.put("purposeValues", purposeValues);
        }
        // 拜访下的图片
        List<Map<String, Object>> photos = gkVisitEntityMapper.selectPhotosByVisitId(visitId);
        result.put("photos", photos);
        return result;
    }

    /**
     * 通过职位ID和筛选条件获取该职位下的GT门店列表
     *
     * @param positionId 职位ID
     * @param page       页码
     * @param pageSize   页容量
     * @param params     筛选参数
     * @return 返回符合条件的GT门店列表
     */
    @Override
    public Page<Map<String, Object>> getGtShopList(String positionId, Map<String, Object> params, int page, int pageSize) {
        String keyword = String.valueOf(params.get("keyword"));
        int timeInterval = Integer.parseInt(String.valueOf(params.get("time")));
        String district = String.valueOf(params.get("district"));
        String level = String.valueOf(params.get("level"));
        return PageHelper.startPage(page, pageSize).doSelectPage(() -> {
            gkVisitEntityMapper.selectGtShopByPositionIdAndQueryParams(
                    positionId, keyword, timeInterval, district, level);
        });
    }

    /**
     * 插入图片到对应的图片表和关联记录到关联表中
     *
     * @param qnKeys     图片的七牛云外链KEY
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param type       图片所属模块功能类型
     * @return 如果插入成功则返回true，反之返回false
     */
    private boolean saveImage(List<String> qnKeys, String empId, String positionId, String foreignId, String type) {
        AtomicBoolean flag;
        flag = new AtomicBoolean(true);
        // 保存前先删除之前的图片
        List<SaPhotoConncetEntity> saPhotoConncetEntityList =
                saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(foreignId, type);
        if (saPhotoConncetEntityList != null && saPhotoConncetEntityList.size() > 0) {
            saPhotoConncetEntityList.forEach(item -> gkVisitPhotoEntityMapper.deleteByPrimaryKey(item.getPhotoId()));
        }
        qnKeys.forEach(key -> {
            if (StringUtils.isNotEmpty(key)) {
                SaPhotoConncetEntity sp = new SaPhotoConncetEntity();
                GkVisitPhotoEntity vp = new GkVisitPhotoEntity();
                vp.setId(UUIDUtils.generateUuid());
                vp.setVisitId(foreignId);
                vp.setPhotoKey(key);
                vp.setPhotoNumber(key);
                if (gkVisitPhotoEntityMapper.insertSelective(vp) <= 0) {
                    LOGGER.info("Save photo: " + key + " into database failed");
                    flag.set(false);
                }
                sp.setId(UUIDUtils.generateUuid());
                sp.setForeignId(vp.getVisitId());
                sp.setPhotoId(vp.getId());
                sp.setCreatePositionId(positionId);
                sp.setCreateEmpId(empId);
                sp.setPhotoType(type);
                if (saPhotoConncetEntityMapper.insertSelective(sp) <= 0) {
                    LOGGER.info("Save photo: " + key + "into connect failed");
                    flag.set(false);
                }
            }
        });
        return flag.get();
    }

    /**
     * 提交拜访
     *
     * @param visitPojo  拜访参数
     * @param positionId 操作人的职位ID
     * @param empId      员工ID
     * @return 如果提交成功返回<strong>"success"</strong>，反之返回响应的错误信息
     */
    @Override
    @Transactional
    public String submitVisit(SubmitVisitPojo visitPojo, String positionId, String empId) {
        // 拜访ID
        String id = String.valueOf(visitPojo.getId());
        GkAccountEntity acc;
        boolean shopUpdFlag;
        if (StringUtils.isEmpty(id) && NULLSTR.equals(id)) {
            LOGGER.info("Given params id is illegal, given by params id= " + id);
            return "ID异常";
        }
        GkVisitEntity visitEntity = gkVisitEntityMapper.selectByPrimaryKey(id);
        if ("已完成".equals(visitEntity.getStatus().toUpperCase())) {
            LOGGER.info("Visit id: " + id + " is already finished");
            return "请勿重复提交";
        }
        String visitType = visitEntity.getVisitType();
        if (NONACCOUNT.equals(visitType)) {
            List<String> photos = visitPojo.getPhotos();
            // 非拜访事项
            visitEntity.setIssue(null);
            visitEntity.setIssueDesc(visitPojo.getIssueDesc());
            visitEntity.setLocationAddr(visitPojo.getLocationAddr());
            List<String> purposes = visitPojo.getPurposes();
            if (!CollectionUtils.isEmpty(purposes)) {
                visitEntity.setPurpose(purposes.get(0));
            }
            if ("submit".equals(String.valueOf(visitPojo.getOperation()))) {
                visitEntity.setStatus("已完成");
            } else {
                visitEntity.setStatus("SA_COMMIT_DRAFT");
            }
            visitEntity.setVisitDateTime(new Date());
            if (!this.saveImage(photos, empId, positionId, id, PhotoTypeContent.VISIT_NONACCOUNT)) {
                return "图片保存失败";
            }
            return gkVisitEntityMapper.updateByPrimaryKeySelective(visitEntity) > 0 ?
                    "success" : "更新状态失败，请重试";
        } else {
            // 门店拜访事项
            // 记录描述
            acc = gkAccountEntityMapper.selectByPrimaryKey(visitEntity.getAccountId());
            if (acc == null) {
                LOGGER.info("Given account id is illegal, given by account id= " + visitEntity.getAccountId());
                return "不存在的拜访门店ID";
            }
            SaVisitContentEntity saVisitContentEntity = saVisitContentEntityMapper.selectFirstByGkVisitId(id);
            boolean isNewContent = false;
            if (saVisitContentEntity == null) {
                saVisitContentEntity = new SaVisitContentEntity();
                isNewContent = true;
            }
            saVisitContentEntity.setGkVisitId(id);
            saVisitContentEntity.setShelfContent(String.valueOf(visitPojo.getShelfContent()));
            saVisitContentEntity.setStackContent(String.valueOf(visitPojo.getStackContent()));
            saVisitContentEntity.setEndFrameContent(String.valueOf(visitPojo.getEndFrameContent()));
            if (isNewContent) {
                if (saVisitContentEntityMapper.insertSelective(saVisitContentEntity) <= 0) {
                    LOGGER.info("Insert new visit content failed");
                    return "保存描述信息失败";
                }
            } else {
                saVisitContentEntity.setRowVersion(saVisitContentEntity.getRowVersion() + 1);
                saVisitContentEntity.setUpdateTime(new Date());
                if (saVisitContentEntityMapper.updateByPrimaryKeySelective(saVisitContentEntity) <= 0) {
                    LOGGER.info("Insert new visit content failed");
                    return "保存描述信息失败";
                }
            }
            // 记录地堆、货架和端架位置，促销单品信息以及推广活动信息
            SaVisitExtEntity saVisitExtEntity = saVisitExtEntityMapper.selectFirstByParentId(id);
            boolean isNewExt = false;
            if (saVisitExtEntity == null) {
                saVisitExtEntity = new SaVisitExtEntity();
                saVisitExtEntity.setParentId(id);
                isNewExt = true;
            }
            saVisitExtEntity.setShelfLocation(visitPojo.getShelfLocation());
            saVisitExtEntity.setStackLocation(visitPojo.getStackLocation());
            saVisitExtEntity.setEndFrameLocation(visitPojo.getEndFrameLocation());
            saVisitExtEntity.setSoftCount(visitPojo.getPromotion().getPromotionSoftCount());
            saVisitExtEntity.setNonCoreCount(visitPojo.getPromotion().getPromotionNonCoreCount());
            saVisitExtEntity.setHadCoreCount(visitPojo.getPromotion().getPromotionHadCoreCount());
            saVisitExtEntity.setPaperCount(visitPojo.getPromotion().getPromotionPaperCount());
            saVisitExtEntity.setWetCount(visitPojo.getPromotion().getPromotionWetCount());
            saVisitExtEntity.setExpandContent(visitPojo.getExpand().getExpandType());

            if (isNewExt) {
                if (saVisitExtEntityMapper.insertSelective(saVisitExtEntity) <= 0) {
                    LOGGER.info("Insert extension message failed.");
                    return "保存扩展信息失败";
                }
            } else {
                saVisitExtEntity.setRowVersion(saVisitExtEntity.getRowVersion() + 1);
                saVisitExtEntity.setUpdateTime(new Date());
                if (saVisitExtEntityMapper.updateByPrimaryKeySelective(saVisitExtEntity) <= 0) {
                    LOGGER.info("Update extension message failed.");
                    return "保存扩展信息失败";
                }
            }

            // 更新门店信息
            visitEntity.setIssue(null);
            visitEntity.setVisitType(visitPojo.getVisitType());
            Map<String, Object> vCodeCntMap = gkAccountVcodeEntityMapper.selectShopVCodeCnt(visitEntity.getAccountId());
            visitEntity.setSkuQty(StringUtil.obj2Int(vCodeCntMap.get("vCodeCnt")));
            if (!(StringUtils.isEmpty(visitPojo.getStackQty()) || NULLSTR.equals(visitPojo.getStackQty()))) {
                visitEntity.setStackQty(new BigDecimal(visitPojo.getStackQty()));
            } else {
                visitEntity.setStackQty(new BigDecimal(0));
            }
            if (!(StringUtils.isEmpty(visitPojo.getShelfQty()) || NULLSTR.equals(visitPojo.getShelfQty()))) {
                visitEntity.setShelfQty(new BigDecimal(visitPojo.getShelfQty()));
            } else {
                visitEntity.setShelfQty(new BigDecimal(0));
            }
            if (!(StringUtils.isEmpty(visitPojo.getEndFrameQty()) || NULLSTR.equals(visitPojo.getEndFrameQty()))) {
                visitEntity.setEndframeQty(new BigDecimal(visitPojo.getEndFrameQty()));
            } else {
                visitEntity.setEndframeQty(new BigDecimal(0));
            }
            Integer shopBooth = StringUtil.obj2Int(visitPojo.getShopBooth());
            Integer shopShelf = StringUtil.obj2Int(visitPojo.getShopShelf());
            Integer shopBracket = StringUtil.obj2Int(visitPojo.getShopBracket());
            if (!shopBracket.equals(acc.getShopbracket())
                    || !shopShelf.equals(acc.getShopshelf())
                    || !shopBooth.equals(acc.getShopbooth())) {
                if ("submit".equals(String.valueOf(visitPojo.getOperation()).toLowerCase())) {
                    if (!this.updateShopInfoWithAccountVisit(acc.getIntid(), shopBooth, shopShelf, shopBracket)) {
                        LOGGER.info("Update shop info to crm failed");
                        return "门店信息更新失败";
                    }
                } else {
                    SaVisitUpdateShopTempEntity saVisitUpdateShopTempEntity =
                            saVisitUpdateShopTempEntityMapper.selectFirstByVisitId(id);
                    boolean isNewUpdate = false;
                    if (saVisitUpdateShopTempEntity == null) {
                        saVisitUpdateShopTempEntity = new SaVisitUpdateShopTempEntity();
                        saVisitUpdateShopTempEntity.setEmpId(empId);
                        saVisitUpdateShopTempEntity.setVisitId(id);
                        isNewUpdate = true;
                    }
                    saVisitUpdateShopTempEntity.setShopBooth(Integer.valueOf(visitPojo.getStackQty()));
                    saVisitUpdateShopTempEntity.setShopBoothTotal(shopBooth);
                    saVisitUpdateShopTempEntity.setShopBracket(Integer.valueOf(visitPojo.getEndFrameQty()));
                    saVisitUpdateShopTempEntity.setShopBracketTotal(shopBracket);
                    saVisitUpdateShopTempEntity.setShopShelf(Integer.valueOf(visitPojo.getShelfQty()));
                    saVisitUpdateShopTempEntity.setShopShelfTotal(shopShelf);
                    if (isNewUpdate) {
                        if (saVisitUpdateShopTempEntityMapper.insertSelective(saVisitUpdateShopTempEntity) <= 0) {
                            LOGGER.info("Insert template shop info failed");
                            return "保存门店堆码端架地堆等基础信息失败";
                        }
                    } else {
                        saVisitUpdateShopTempEntity.setUpdateTime(new Date());
                        saVisitUpdateShopTempEntity.setRowVersion(saVisitUpdateShopTempEntity.getRowVersion() + 1);
                        if (saVisitUpdateShopTempEntityMapper.
                                updateByPrimaryKeySelective(saVisitUpdateShopTempEntity) <= 0) {
                            LOGGER.info("Update template shop info failed");
                            return "保存门店堆码端架地堆等基础信息失败";
                        }
                    }
                }
                acc.setShopbooth(StringUtil.obj2Int(visitPojo.getShopBooth()));
                acc.setShopshelf(StringUtil.obj2Int(visitPojo.getShopShelf()));
                acc.setShopbracket(StringUtil.obj2Int(visitPojo.getShopBracket()));
            }
            // 设置定位
            visitEntity.setLocationAddr(visitPojo.getLocationAddr());
            // 设置拜访目的
            List<String> purposes = visitPojo.getPurposes();
            if (!CollectionUtils.isEmpty(purposes)) {
                String purpose = StringUtils.join(purposes, ",");
                visitEntity.setPurpose(purpose);
            }

            shopUpdFlag = true;
            acc.setVisitlastdate(visitEntity.getVisitDate());
            gkAccountEntityMapper.updateByPrimaryKey(acc);

            // 保存竞品记录
            if (visitPojo.getCompetitors().size() > 0) {
                if (!this.saveCompetitor(
                        empId, positionId, visitEntity.getAccountId(), id, visitPojo.getCompetitors())) {
                    LOGGER.info("Save competitors info failed");
                    return "保存竞品记录失败";
                }
            }

            // 保存V码
            if (visitPojo.getVCodes().size() > 0) {
                if (!this.submitVCodeList(visitPojo.getVCodes(),
                        visitEntity.getAccountId(),
                        id, empId, positionId)) {
                    LOGGER.info("Save vcode info failed");
                    return "保存V码记录失败";
                }
            }

            // 清理上一次的照片记录
            int deleteCount = saPhotoConncetEntityMapper.deleteByForeignId(id);
            LOGGER.info("Delete all overdue photos by visit, delete row: " + deleteCount);

            // 保存拜访照片
            List<String> stackPhotos = visitPojo.getStackPhotos();
            if (!this.saveImage(stackPhotos, empId, positionId, id, PhotoTypeContent.VISIT_STACK)) {
                return "地堆照片保存失败";
            }

            List<String> shelfQty = visitPojo.getShelfPhotos();
            if (!this.saveImage(shelfQty, empId, positionId, id, PhotoTypeContent.VISIT_SHELF)) {
                return "货架照片保存失败";
            }

            List<String> endframeQty = visitPojo.getEndFramePhotos();
            if (!this.saveImage(endframeQty, empId, positionId, id, PhotoTypeContent.VISIT_ENDFRAME)) {
                return "端架照片保存失败";
            }

            List<String> promotionPhotos = visitPojo.getPromotion().getPromotionPhotos();
            if (!this.saveImage(promotionPhotos, empId, positionId, id, PhotoTypeContent.VISIT_PROMOTE)) {
                return "促销单品图片保存失败";
            }

            List<String> expandPhotos = visitPojo.getExpand().getExpandPhotos();
            if (!this.saveImage(expandPhotos, empId, positionId, id, PhotoTypeContent.VISIT_EXPAND)) {
                return "推广活动图片保存失败";
            }
        }

        if ("submit".equals(visitPojo.getOperation().toLowerCase())) {
            //记录拜访提交次数
            GkDataEmpEntity empData = gkDataEmpMapper.selectByPositionIdAndRecordDate(positionId, new Date()).get(0);

            //非客户拜访
            if (NONACCOUNT.equals(visitType)) {
                empData.setVisitNonAccntCnt(empData.getVisitNonAccntCnt() + 1);
            }
            //记录门店修改次数
            if (shopUpdFlag) {
                empData.setShopUpdateCnt(empData.getShopUpdateCnt() + 1);
            }
            empData.setVisitCnt(empData.getVisitCnt() + 1);
            if (gkDataEmpMapper.updateByPrimaryKeySelective(empData) <= 0) {
                LOGGER.info("Update account edit count failed");
            }

            // 保存拜访
            if ("submit".equals(String.valueOf(visitPojo.getOperation()).toLowerCase())) {
                visitEntity.setStatus("已完成");
            } else {
                visitEntity.setStatus("SA_COMMIT_DRAFT");
            }
            visitEntity.setVisitDateTime(new Date());
            if (gkVisitEntityMapper.updateByPrimaryKeySelective(visitEntity) <= 0) {
                return "更新状态失败，请重试";
            }
        }

        return "success";
    }

    /**
     * 门店拜访任务提交时，向CRM更新该门店的堆头、货架和端架信息
     *
     * @param accountId   门店ID
     * @param shopBooth   堆头数
     * @param shopShelf   货架数
     * @param shopBracket 端架数
     * @return 如果更新成功则返回true，反之返回false
     */
    private boolean updateShopInfoWithAccountVisit(String accountId, Integer shopBooth, Integer shopShelf, Integer shopBracket) {
        GkAccountEntity accountInfo = gkAccountEntityMapper.selectFirstByIntid(accountId);
        Map<String, Object> params = MapUtil.objectToMap(accountInfo);
        if (params == null) {
            return false;
        }
        params.put("attr04", shopBooth);
        params.put("attr03", shopShelf);
        params.put("attr02", shopBracket);
        params.put("attr01", accountInfo.getIntid());
        params.put("objectname", "UpdateAllInfo");
        params.put("action", "\"document/http://siebel.com/CustomUI:VDWeChatIntegrationWSInvokeMethod\"");
        params.put("inName", "VDWeChatIntegrationWSInvokeMethod_Input");
        generalFeignService.generalRequest(params);
        return true;
    }

    /**
     * 取消拜访
     *
     * @param params 取消参数
     * @return 如果成功则返回<strong>"success"</strong>，反之将返回相应的错误信息
     */
    @Override
    public String cancelVisit(Map<String, String> params) {
        String cancelReason = params.get("cancelReason");
        String id = params.get("id");
        GkVisitEntity visit = gkVisitEntityMapper.selectByPrimaryKey(id);
        if (visit == null) {
            return "参数错误";
        }
        if (!"SA_COMMIT".equals(visit.getStatus()) && !"SA_COMMIT_DRAFT".equals(visit.getStatus())) {
            return "当前状态下不能取消";
        }
        visit.setStatus("已取消");
        visit.setCancelReason(cancelReason);
        visit.setCanceledTime(new Date());
        if (gkVisitEntityMapper.updateByPrimaryKeySelective(visit) <= 0) {
            return "状态更新失败";
        }
        //记录拜访取消次数
        GkDataEmpEntity empData =
                gkDataEmpMapper.selectByPositionIdAndRecordDate(visit.getPositionId(), new Date()).get(0);
        empData.setVisitCancelCnt(empData.getVisitCancelCnt() + 1);
        if (gkDataEmpMapper.updateByPrimaryKeySelective(empData) <= 0) {
            return "拜访次数记录失败";
        }
        return "success";
    }

    /**
     * 删除拜访
     *
     * @param id 拜访ID
     * @return 如果成功则返回<strong>"success"</strong>，反之将返回相应的错误信息
     */
    @Override
    public String deleteVisit(String id) {
        String delete = "SA_DELETE";
        if (StringUtils.isNotEmpty(id) && !(NULLSTR.equals(id))) {
            GkVisitEntity gkVisitEntity = gkVisitEntityMapper.selectByPrimaryKey(id);
            if (delete.equals(gkVisitEntity.getStatus())) {
                return "该拜访当前已处于删除状态";
            }
            gkVisitEntity.setStatus(delete);
            if (gkVisitEntityMapper.updateByPrimaryKeySelective(gkVisitEntity) <= 0) {
                return "删除拜访任务失败";
            }
        } else {
            return "传入参数错误";
        }
        return "success";
    }

    /**
     * 保存拜访图片
     *
     * @param accountName 门店名
     * @param type        类型
     * @param photoIds    图片数组
     * @return 如果保存成功返回所有图片对应的七牛云外链，如果包含上传失败的内容，则该图片外链将为"null"
     */
    @Override
    public Map<String, Object> saveVisitPhoto(String accountName, String type, List<String> photoIds) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> keys = this.getImageKeys(photoIds,
                accountName + "_" + type + "_" + UUIDUtils.generateShortUuid());
        result.put("photos", keys);
        result.put("prefix", paramsConfig.getPhotoPrefix());
        return result;
    }

    /**
     * 通过竞品ID和拜访ID获取竞品详情
     *
     * @param competitorId 竞品ID
     * @param visitId      拜访ID
     * @return 竞品详情
     */
    @Override
    public CompetitorPojo getCompetitorDetail(String competitorId, String visitId) {
        CompetitorPojo competitorPojo = new CompetitorPojo();
        GkCompetitorEntity competitorEntity = gkCompetitorMapper.selectByPrimaryKey(competitorId);
        // 设置基本信息
        competitorPojo.setId(competitorEntity.getId());
        competitorPojo.setBarCode(competitorEntity.getBarCodeNumber());
        competitorPojo.setBrand(competitorEntity.getBrand());
        competitorPojo.setName(competitorEntity.getProductName());
        competitorPojo.setNorms(competitorEntity.getSpecification());

        // 设置宽度
        if (StringUtils.isEmpty(competitorEntity.getProdWidth()) || NULLSTR.equals(competitorEntity.getProdWidth())) {
            competitorPojo.setWidth(0d);
        } else {
            competitorPojo.setWidth(Double.parseDouble(competitorEntity.getProdWidth()));
        }
        // 设置高度
        if (StringUtils.isEmpty(competitorEntity.getProdHeight()) || NULLSTR.equals(competitorEntity.getProdHeight())) {
            competitorPojo.setHeight(0d);
        } else {
            competitorPojo.setHeight(Double.parseDouble(competitorEntity.getProdHeight()));
        }
        // 设置深度
        if (StringUtils.isEmpty(competitorEntity.getProdDepth()) || NULLSTR.equals(competitorEntity.getProdDepth())) {
            competitorPojo.setDepth(0d);
        } else {
            competitorPojo.setDepth(Double.parseDouble(competitorEntity.getProdDepth()));
        }
        // 设置重量
        if (StringUtils.isEmpty(competitorEntity.getNetweight()) || NULLSTR.equals(competitorEntity.getNetweight())) {
            competitorPojo.setWeight(0d);
        } else {
            competitorPojo.setWeight(Double.parseDouble(competitorEntity.getNetweight()));
        }

        // 设置促销信息
        GkAccountCompetitorEntity gkAccountCompetitorEntity =
                gkAccountCompetitorEntityMapper.selectFirstByCompetitorIdAndVisitId(competitorId, visitId);
        if (gkAccountCompetitorEntity != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            competitorPojo.setPromotionAction(gkAccountCompetitorEntity.getPromotionType());
            if (gkAccountCompetitorEntity.getStartDateOfPmt() != null) {
                competitorPojo.setPromotionStartTime(dateFormat.format(gkAccountCompetitorEntity.getStartDateOfPmt()));
            }
            if (gkAccountCompetitorEntity.getEndDateOfPmt() != null) {
                competitorPojo.setPromotionEndTime(dateFormat.format(gkAccountCompetitorEntity.getEndDateOfPmt()));
            }
            // 设置价格，价格由于每家门店定价会有不同，故价格来源与拜访绑定
            competitorPojo.setPrice(Double.parseDouble(gkAccountCompetitorEntity.getSalesPrice().toPlainString()));
        } else {
            competitorPojo.setPrice(0d);
        }

        // 设置竞品图片
        List<GkCompetitorPhotoEntity> gkCompetitorPhotoEntities =
                gkCompetitorPhotoMapper.selectByCompetitorIdAndVisitId(competitorId, visitId);
        if (gkCompetitorPhotoEntities != null && gkCompetitorPhotoEntities.size() > 0) {
            List<String> photos = new ArrayList<>();
            gkCompetitorPhotoEntities.forEach(item -> photos.add(item.getPhotoKey()));
            competitorPojo.setPhotos(photos);
        }
        return competitorPojo;
    }

    /**
     * 将图片连续上传至七牛云，并返回所有的图片外链KEY
     *
     * @param serverIds 微信临时资源ID
     * @param fileName  指定文件名
     * @return 七牛云外链KEY
     */
    @Deprecated
    private List<Map<String, String>> getImageKeys(List<String> serverIds, String fileName) {
        List<Map<String, String>> keys = new ArrayList<>();
        serverIds.forEach(id -> {
            Map<String, String> map = new HashMap<>();
            map.put("key", String.valueOf(generalFeignService.upLoadToQiNiuYun(id, fileName).getData()));
            map.put("id", id);
            keys.add(map);
        });
        return keys;
    }

    /**
     * 更新竞品信息
     *
     * @param params 参数
     * @return 如果更新成功则返回true，反之返回false
     */
    @Override
    public boolean updateCompetitor(Map<String, Object> params) {
        String id = String.valueOf(params.get("id"));
        GkCompetitorEntity gkCompetitorEntity = gkCompetitorMapper.selectByPrimaryKey(id);
        gkCompetitorEntity.setUpdatedTime(new Date());
        gkCompetitorEntity.setBrand(this.returnNullStr(params.get("brand")));
        gkCompetitorEntity.setProductName(this.returnNullStr(params.get("productName")));
        gkCompetitorEntity.setProdDepth(this.returnNullStr(params.get("prodDepth")));
        gkCompetitorEntity.setProdHeight(this.returnNullStr(params.get("prodHeight")));
        gkCompetitorEntity.setProdWidth(this.returnNullStr(params.get("prodWidth")));
        gkCompetitorEntity.setNetweight(this.returnNullStr(params.get("netweight")));
        gkCompetitorEntity.setSpecification(this.returnNullStr(params.get("specification")));
        if (gkCompetitorMapper.updateByPrimaryKeySelective(gkCompetitorEntity) > 0) {
            return true;
        }
        LOGGER.info("Update competitor failed.");
        return false;
    }

    /**
     * 新增竞品
     *
     * @param empId       用户ID
     * @param positionId  职位ID
     * @param accountId   门店ID
     * @param visitId     拜访ID
     * @param competitors 竞品信息
     * @return 如果新增成功则返回true，反之返回false
     */
    @Override
    @Transactional
    public boolean saveCompetitor(String empId,
                                  String positionId,
                                  String accountId,
                                  String visitId,
                                  List<CompetitorPojo> competitors) {
        // 新增图片前删除之前的图片
        List<SaVisitCompetitorPhotoConnectEntity> saVisitCompetitorPhotoConnectEntities =
                saVisitCompetitorPhotoConnectEntityMapper.selectByVisitId(visitId);
        if (saVisitCompetitorPhotoConnectEntities != null && saVisitCompetitorPhotoConnectEntities.size() > 0) {
            saVisitCompetitorPhotoConnectEntities.forEach(item -> {
                if (gkCompetitorPhotoMapper.deleteByPrimaryKey(item.getPhotoId()) > 0) {
                    saVisitCompetitorPhotoConnectEntityMapper.deleteByPrimaryKey(item.getId());
                }
            });
        }
        for (CompetitorPojo competitor : competitors) {
            String barCodeNumber = competitor.getBarCode();
            String competitorId = competitor.getId();
            // 查找该竞品是否已经在该拜访下绑定过该门店
            boolean isAlreadyExist = false;
            List<GkAccountCompetitorEntity> gkAccountCompetitorEntities = gkAccountCompetitorEntityMapper
                    .selectByAccountIdAndBarCodeNumberAndVisitIdAndCompetitorId(
                            accountId, barCodeNumber, visitId, competitorId);
            if (gkAccountCompetitorEntities != null && gkAccountCompetitorEntities.size() > 0) {
                isAlreadyExist = true;
                LOGGER.info("This competitor is already exist in this visit.");
            }

            double salesPrice = competitor.getPrice();
            double promotionPrice = 0.0;

            String promotionType = competitor.getPromotionAction();
            String startDateOfPmt = competitor.getPromotionStartTime();
            String endDateOfPmt = competitor.getPromotionEndTime();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            GkAccountCompetitorEntity ac;
            if (isAlreadyExist) {
                ac = gkAccountCompetitorEntities.get(0);
            } else {
                ac = new GkAccountCompetitorEntity();
            }

            ac.setEmpId(empId);
            ac.setPositionId(positionId);
            ac.setAccountId(accountId);
            ac.setPromotionType(promotionType);
            try {
                if (StringUtils.isNotEmpty(startDateOfPmt) && !NULLSTR.equals(startDateOfPmt)) {
                    if (StringUtils.isNotEmpty(endDateOfPmt) && !NULLSTR.equals(endDateOfPmt)) {
                        ac.setStartDateOfPmt(dateFormat.parse(startDateOfPmt));
                        ac.setEndDateOfPmt(dateFormat.parse(endDateOfPmt));
                    }
                }
            } catch (ParseException e) {
                LOGGER.info("Failed to cover start or end time to date object.");
                return false;
            }
            ac.setBarCodeNumber(barCodeNumber);
            ac.setCompetitorId(competitorId);
            ac.setVisitId(visitId);
            ac.setSalesPrice(BigDecimal.valueOf(salesPrice));
            ac.setPromotionPrice(BigDecimal.valueOf(promotionPrice));

            if (isAlreadyExist) {
                ac.setUpdatedTime(new Date());
                if (gkAccountCompetitorEntityMapper.updateByPrimaryKeySelective(ac) <= 0) {
                    LOGGER.info("Update relationship between account and competitor failed.Competitor id:"
                            + ac.getCompetitorId());
                    continue;
                }
            } else {
                if (gkAccountCompetitorEntityMapper.insertSelective(ac) <= 0) {
                    LOGGER.info("Insert new relationship between account and competitor failed.Competitor id:"
                            + ac.getCompetitorId());
                    continue;
                }
            }

            //记录竞品保存次数
            GkDataEmpEntity empData = gkDataEmpMapper.selectByPositionIdAndRecordDate(positionId, new Date()).get(0);
            empData.setCompetitorCnt(empData.getCompetitorCnt() + 1);
            if (gkDataEmpMapper.updateByPrimaryKeySelective(empData) <= 0) {
                LOGGER.info("Insert emp data count failed.");
                return false;
            }

            //处理图片
            GkCompetitorPhotoEntity newPhoto;
            for (String photo : competitor.getPhotos()) {
                newPhoto = new GkCompetitorPhotoEntity();
                newPhoto.setCompetitorId(competitorId);
                newPhoto.setPhotoKey(photo);
                newPhoto.setEmpId(empId);
                if (gkCompetitorPhotoMapper.insertSelective(newPhoto) <= 0) {
                    LOGGER.info("Save competitor photos failed.");
                    return false;
                }
                SaVisitCompetitorPhotoConnectEntity saVisitCompetitorPhotoConnectEntity =
                        new SaVisitCompetitorPhotoConnectEntity();
                saVisitCompetitorPhotoConnectEntity.setCompetitorId(ac.getCompetitorId());
                saVisitCompetitorPhotoConnectEntity.setPhotoId(newPhoto.getId());
                saVisitCompetitorPhotoConnectEntity.setVisitId(visitId);
                if (saVisitCompetitorPhotoConnectEntityMapper.insertSelective(
                        saVisitCompetitorPhotoConnectEntity) <= 0) {
                    LOGGER.info("Save competitor and visit connect failed.");
                    return false;
                }
            }
        }
        return true;
    }

    private String returnNullStr(Object obj) {
        String isNull = "null";
        String value = String.valueOf(obj);
        if (StringUtils.isBlank(value) || isNull.equals(value)) {
            return "";
        }
        return value;
    }

    /**
     * 通过拜访ID获取该拜访下的竞品列表
     *
     * @param visitId 拜访ID
     * @return 返回该拜访下的所有竞品列表
     */
    @Override
    public List<Map<String, Object>> getVisitCompetitorList(String visitId) {
        return gkAccountCompetitorEntityMapper.selectVisitCompetitorListByVisitId(visitId);
    }

    /**
     * 通过拜访和门店ID获取符合条件的V码、库存等信息
     *
     * @param visitId   拜访ID
     * @param accountId 门店ID
     * @return 返回符合条件的信息
     */
    @Override
    public List<Map<String, Object>> getListForOneVisit(String visitId, String accountId) {
        if (StringUtils.isNotBlank(accountId)) {
            this.getVisitStock(visitId, accountId);
        }
        return gkAccountVcodeEntityMapper.selectVCodeInfoByVisitId(visitId);
    }

    /**
     * （由于拜访计划添加库存不能删除全部）
     * 此查询用来做同步删除
     *
     * @param visitId   拜访ID
     * @param accountId 门店ID
     */
    private void getVisitStock(String visitId, String accountId) {
        List<String> list1 = this.mapListShiftStringList(
                gkAccountVcodeEntityMapper.selectStockOrdersIdByAccountId(accountId, visitId));

        List<String> list2 = this.mapListShiftStringList(
                gkAccountVcodeEntityMapper.selectStockOrdersIdByVisitId(visitId)
        );

        // 移除两次查询中存在的不同的内容
        List<String> list3 = this.getDiffrent(list2, list1);
        if (list3.size() > 0) {
            for (String id : list3) {
                gkAccountVcodeEntityMapper.deleteByPrimaryKey(id);
                LOGGER.info("Success to delete account-stock witch order id is: " + id);
            }
        }
    }

    private List<String> mapListShiftStringList(List<Map<String, Object>> list) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            returnList.add(i, String.valueOf(list.get(i).get("id")));
        }
        return returnList;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1 需要对比的LIST
     * @param list2 需要对比的LIST
     * @return 返回其中不同的元素组成的LIST
     */
    private List<String> getDiffrent(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<>();
        for (String str : list1) {
            if (!list2.contains(str)) {
                diff.add(str);
            }
        }
        return diff;

    }

    /**
     * 通过相关参数查询该拜访下的V码列表
     *
     * @param params 查询参数
     * @return 返回符合条件的V码列表
     */
    @Override
    public List<Map<String, Object>> loadCommonVCodeList(Map<String, Object> params) {
        String visitId = (String) params.get("visitId");
        String accountId = (String) params.get("accountId");

        Map<String, Object> qp = new HashMap<>();
        String todayStr = LocalDate.now().toString();

        List<Map<String, Object>> result;

        //查出今天是否有保存过库存
        int isPass =
                gkStockOrderEntityMapper.selectCountByVisitIdAndAccountIdAndToday(visitId, accountId, todayStr);

        //正常查询
        if (isPass > 0) {
            result = gkStockOrderEntityMapper.selectVCodeInfoByAccountIdAndVisitIdAndToday(accountId, visitId, todayStr);
        } else {
            //拿出上次提交成功的库存
            result = gkStockOrderEntityMapper.selectExistVCodeInfoByAccountIdAndVisitIdAndToday(accountId, visitId, todayStr);
        }

        return result;
    }

    /**
     * 通过关键字和默认V码分页查询V码列表
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param params   相关参数
     * @return 返回符合条件的分页结果
     */
    @Override
    public Page<Map<String, Object>> getVCodeList(int page, int pageSize, Map<String, Object> params) {
        String keyword = (String) params.get("keyword");
        List<String> initVCodeList = (List<String>) params.get("initVCodeList");
        return PageHelper.startPage(page, pageSize).doSelectPage(() ->
                gkListOfValueEntityMapper.selectLovInfoByInitCodeAndKeyword(keyword, initVCodeList)
        );
    }

    /**
     * 保存V码列表
     *
     * @param params 保存参数
     * @return 如果保存成功则返回true，反之返回false
     */
    @Override
    public boolean saveAccountVCode(Map<String, Object> params) {
        List<String> vCodeList = (List<String>) params.get("vCodeList");
        String accountId = (String) params.get("accountId");
        List<String> removedList = (List<String>) params.get("removedList");

        // 移除VCODE
        if (!CollectionUtils.isEmpty(removedList)) {
            for (String vCode : removedList) {
                GkAccountVcodeEntity accountVCode =
                        gkAccountVcodeEntityMapper.selectByAccountIdAndVCode(accountId, vCode).get(0);
                if (accountVCode != null) {
                    if (gkAccountVcodeEntityMapper.deleteByPrimaryKey(accountVCode.getId()) <= 0) {
                        LOGGER.info("Remove vcode:[" + vCode + "] failed.");
                        return false;
                    }
                }
            }
        }

        // 添加VCODE
        if (!CollectionUtils.isEmpty(vCodeList)) {
            for (String vCode : vCodeList) {
                GkAccountVcodeEntity accountVCode =
                        gkAccountVcodeEntityMapper.selectByAccountIdAndVCode(accountId, vCode).get(0);
                if (accountVCode != null) {
                    continue;
                }
                accountVCode = new GkAccountVcodeEntity();
                accountVCode.setAccountId(accountId);
                accountVCode.setVCode(vCode);
                if (gkAccountVcodeEntityMapper.insertSelective(accountVCode) <= 0) {
                    LOGGER.info("Insert new vcode:[" + vCode + "] failed.");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 保存V码列表
     *
     * @param vCodePojoList V码列表
     * @param accountId     门店ID
     * @param empId         提交人人员ID
     * @param visitId       拜访ID
     * @param positionId    提交人职位ID
     * @return 如果保存成功返回true，反之返回false
     */
    private boolean submitVCodeList(List<VCodePojo> vCodePojoList,
                                    String accountId,
                                    String visitId,
                                    String empId,
                                    String positionId) {
        // 移除上个版本的V码库存信息
        gkStockOrderEntityMapper.deleteByVisitIdAndAccountIdAndEmpId(visitId, accountId, empId);
        if (!CollectionUtils.isEmpty(vCodePojoList)) {
            for (VCodePojo vCode : vCodePojoList) {
                // 查询该V码是否已与该门店绑定
                List<GkAccountVcodeEntity> accountVCodeList =
                        gkAccountVcodeEntityMapper.selectByAccountIdAndVCode(accountId, vCode.getId());
                if (accountVCodeList == null || accountVCodeList.size() == 0) {
                    GkAccountVcodeEntity accountVCode = new GkAccountVcodeEntity();
                    accountVCode.setAccountId(accountId);
                    accountVCode.setVCode(vCode.getId());
                    if (gkAccountVcodeEntityMapper.insertSelective(accountVCode) <= 0) {
                        LOGGER.info("Insert new vcode:[" + vCode + "] failed.");
                        return false;
                    }
                }
                // 保存V码库存等信息
                GkStockOrderEntity aStockAndOrderMask = new GkStockOrderEntity();
                aStockAndOrderMask.setVCode(vCode.getId());
                aStockAndOrderMask.setAccountId(accountId);
                aStockAndOrderMask.setVisitId(visitId);
                aStockAndOrderMask.setEmpId(empId);
                aStockAndOrderMask.setPositionId(positionId);
                aStockAndOrderMask.setWriteTime(new Date());
                aStockAndOrderMask.setOrderQty(vCode.getOrder());
                aStockAndOrderMask.setStockQty(vCode.getStock());
                aStockAndOrderMask.setPrice(new BigDecimal(String.valueOf(vCode.getPrice())));
                gkStockOrderEntityMapper.insertSelective(aStockAndOrderMask);
            }
        }
        return true;
    }

    /**
     * 获取已完成的拜访基础数据及对比
     *
     * @param empId   员工ID
     * @param visitId 拜访ID
     * @return 该拜访的基本信息和数据
     */
    @Override
    public Map<String, Object> getVisitAndCompare(String empId, String visitId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> visit = this.getVisitByVisitIdAndEmpId(visitId, empId);
        result.put("visit", visit);
        result.put("compare", this.getCompareInfo(visit));
        return result;
    }

    private Map<String, Object> getCompareInfo(Map<String, Object> visit) {
        Map<String, Object> compare = new HashMap<>();
        // SKU数量
        compare.put("cSkuQty", 0);
        // 堆头数量
        compare.put("cStackQty", 0);
        // 货架数量
        compare.put("cShelfQty", 0);
        // 端架数量
        compare.put("cEndframeQty", 0);

        String accountId = (String) visit.get("accountId");
        Date visitDate = (Date) visit.get("visitDateTime");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String dateStr = dateFormat.format(visitDate);

        Map<String, Object> result =
                gkVisitEntityMapper.selectVisitBaseInfoByAccountIdAndVisitDate(accountId, dateStr);
        if (result != null && !StringUtil.isNull((String) result.get("id"))) {
            Integer skuQtyCurr = visit.get("skuQty") == null ? 0 : (Integer) visit.get("skuQty");
            Integer skuQtyLast = result.get("skuQty") == null ? 0 : (Integer) result.get("skuQty");

            BigDecimal stackQtyCurr = visit.get("stackQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) visit.get("stackQty");
            BigDecimal stackQtyLast = result.get("stackQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) result.get("stackQty");

            BigDecimal endframeQtyCurr = visit.get("endframeQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) visit.get("endframeQty");
            BigDecimal endframeQtyLast = result.get("endframeQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) result.get("endframeQty");

            BigDecimal shelfQtyCurr = visit.get("shelfQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) visit.get("shelfQty");
            BigDecimal shelfQtyLast = result.get("shelfQty") == null ?
                    BigDecimal.ZERO : (BigDecimal) result.get("shelfQty");

            compare.put("cSkuQty", skuQtyCurr - skuQtyLast);
            compare.put("cStackQty", stackQtyCurr.subtract(stackQtyLast));
            compare.put("cShelfQty", shelfQtyCurr.subtract(shelfQtyLast));
            compare.put("cEndframeQty", endframeQtyCurr.subtract(endframeQtyLast));
        }
        return compare;
    }

    /**
     * 通过拜访ID获取该拜访下的图片列表
     *
     * @param visitId 拜访ID
     * @return 该拜访下的图片列表
     */
    @Override
    public List<Map<String, Object>> getVisitPhotos(String visitId) {
        List<GkVisitPhotoEntity> photos = gkVisitPhotoEntityMapper.selectByVisitIdOrderByCreatedTime(visitId);
        List<Map<String, Object>> results = new ArrayList<>();
        photos.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("key", paramsConfig.getPhotoPrefix() + item.getPhotoKey());
            results.add(map);
        });
        return results;
    }

    /**
     * 根据条件分页查询拜访记录
     *
     * @param accountId  门店Id
     * @param empId      员工Id
     * @param positionId 职位Id
     * @param visitDate  拜访日期
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @return 返回指定条件下的拜访记录
     */
    @Override
    public Page<Map<String, Object>> getVisitMissionByCondition(String accountId,
                                                                String empId,
                                                                String positionId,
                                                                String visitDate,
                                                                int pageNum,
                                                                int pageSize) {
        Page<Map<String, Object>> result = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            gkVisitEntityMapper.selectVisitMissionByCondition(accountId, empId, positionId, visitDate);
        });
        return result;
    }

    /**
     * 通过拜访ID获取非拜访事项详情
     *
     * @param visitId 拜访ID
     * @return 获取非拜访事项详情
     */
    @Override
    public NonAccountVisitDetailPojo getNonAccountVisitDetail(String visitId) {
        GkVisitEntity gkVisitEntity = gkVisitEntityMapper.selectByPrimaryKey(visitId);
        if (gkVisitEntity == null) {
            return null;
        }
        NonAccountVisitDetailPojo nonAccountVisitDetailPojo = new NonAccountVisitDetailPojo();
        nonAccountVisitDetailPojo.setId(gkVisitEntity.getId());
        nonAccountVisitDetailPojo.setPurpose(gkVisitEntity.getPurpose());
        nonAccountVisitDetailPojo.setLocationAddr(gkVisitEntity.getLocationAddr());
        nonAccountVisitDetailPojo.setContent(gkVisitEntity.getIssueDesc());
        nonAccountVisitDetailPojo.setPrefix(paramsConfig.getPhotoPrefix());
        nonAccountVisitDetailPojo.setStatus(gkVisitEntity.getStatus());
        List<String> photos = new ArrayList<>();
        saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_NONACCOUNT).forEach(
                photo -> {
                    GkVisitPhotoEntity gkVisitPhotoEntity
                            = gkVisitPhotoEntityMapper.selectByPrimaryKey(photo.getPhotoId());
                    if (gkVisitPhotoEntity != null) {
                        photos.add(gkVisitPhotoEntity.getPhotoKey());
                    }
                });
        nonAccountVisitDetailPojo.setPhotos(photos);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = gkVisitEntity.getVisitDate();
        nonAccountVisitDetailPojo.setVisitDate(dateFormat.format(date));
        return nonAccountVisitDetailPojo;
    }

    /**
     * 通过拜访ID获取门店拜访事项详情
     *
     * @param visitId 拜访ID
     * @return 获取门店拜访事项详情
     */
    @Override
    public AccountVisitDetailPojo getRealAccountVisitDetail(String visitId) {
        GkVisitEntity gkVisitEntity = gkVisitEntityMapper.selectByPrimaryKey(visitId);

        if (gkVisitEntity == null) {
            LOGGER.info("Visit id: " + visitId + "is invalid");
            return null;
        }
        GkAccountEntity gkAccountEntity = gkAccountEntityMapper.selectByPrimaryKey(gkVisitEntity.getAccountId());
        if (gkAccountEntity == null) {
            LOGGER.info("Visit id: " + visitId + "did not contains a correct account id");
            return null;
        }
        AccountVisitDetailPojo accountVisitDetailPojo = new AccountVisitDetailPojo();
        accountVisitDetailPojo.setStatus(gkVisitEntity.getStatus());
        accountVisitDetailPojo.setPrefix(paramsConfig.getPhotoPrefix());
        SaVisitExtEntity saVisitExtEntity = saVisitExtEntityMapper.selectFirstByParentId(visitId);
        // 设置扩展信息
        if (saVisitExtEntity != null) {
            List<SaPhotoConncetEntity> expandPhotosList =
                    saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_EXPAND);

            // 设置推广信息
            ExpandPojo expandPojo = new ExpandPojo();
            // 设置推广图
            if (expandPhotosList.size() > 0) {
                List<String> expandPhotos = new ArrayList<>();
                expandPhotosList.forEach(item -> {
                    GkVisitPhotoEntity gkVisitPhotoEntity =
                            gkVisitPhotoEntityMapper.selectByPrimaryKey(item.getPhotoId());
                    if (gkVisitPhotoEntity != null) {
                        expandPhotos.add(gkVisitPhotoEntity.getPhotoKey());
                    }
                });
                expandPojo.setExpandPhotos(expandPhotos);
            }
            expandPojo.setExpandType(saVisitExtEntity.getExpandContent());
            accountVisitDetailPojo.setExpand(expandPojo);

            // 设置促销信息
            List<SaPhotoConncetEntity> promotionPhotoList =
                    saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_PROMOTE);
            PromotionPojo promotionPojo = new PromotionPojo();
            if (promotionPhotoList.size() > 0) {
                List<String> promotionPhotos = new ArrayList<>();
                promotionPhotoList.forEach(item -> {
                    GkVisitPhotoEntity gkVisitPhotoEntity =
                            gkVisitPhotoEntityMapper.selectByPrimaryKey(item.getPhotoId());
                    if (gkVisitPhotoEntity != null) {
                        promotionPhotos.add(gkVisitPhotoEntity.getPhotoKey());
                    }
                });
                promotionPojo.setPromotionPhotos(promotionPhotos);
            }
            promotionPojo.setPromotionHadCoreCount(saVisitExtEntity.getHadCoreCount());
            promotionPojo.setPromotionNonCoreCount(saVisitExtEntity.getNonCoreCount());
            promotionPojo.setPromotionPaperCount(saVisitExtEntity.getPaperCount());
            promotionPojo.setPromotionSoftCount(saVisitExtEntity.getSoftCount());
            promotionPojo.setPromotionWetCount(saVisitExtEntity.getWetCount());
            accountVisitDetailPojo.setPromotion(promotionPojo);

            // 位置信息
            accountVisitDetailPojo.setStackLocation(saVisitExtEntity.getStackLocation());
            accountVisitDetailPojo.setShelfLocation(saVisitExtEntity.getShelfLocation());
            accountVisitDetailPojo.setEndFrameLocation(saVisitExtEntity.getEndFrameLocation());
        }

        // 设置堆货架、地堆信息
        // 数量信息
        // 地堆/货架/端架
        Map<String, Object> visitMap = gkVisitEntityMapper.selectVisitByIdAndEmpId(visitId);

        if (visitMap != null) {
            accountVisitDetailPojo.setStackQty(String.valueOf(visitMap.get("stackQty")));
            accountVisitDetailPojo.setShelfQty(String.valueOf(visitMap.get("shelfQty")));
            accountVisitDetailPojo.setEndFrameQty(String.valueOf(visitMap.get("endFrameQty")));
            accountVisitDetailPojo.setShopBooth(String.valueOf(visitMap.get("shopBooth")));
            accountVisitDetailPojo.setShopShelf(String.valueOf(visitMap.get("shopShelf")));
            accountVisitDetailPojo.setShopBracket(String.valueOf(visitMap.get("shopBracket")));
            // 对比信息
            Map<String, Object> compare = this.getCompareInfo(visitMap);
            accountVisitDetailPojo.setShelfCompareCount(String.valueOf(compare.get("cShelfQty")));
            accountVisitDetailPojo.setEndFrameCompareCount(String.valueOf(compare.get("cEndframeQty")));
            accountVisitDetailPojo.setStackCompareCount(String.valueOf(compare.get("cStackQty")));
            accountVisitDetailPojo.setVCodeCount(String.valueOf(compare.get("cSkuQty")));
        }

        // 设置图片信息
        List<SaPhotoConncetEntity> shelfPhotoList =
                saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_SHELF);
        if (shelfPhotoList.size() > 0) {
            List<String> shelfPhotos = new ArrayList<>();
            shelfPhotoList.forEach(item -> {
                GkVisitPhotoEntity gkVisitPhotoEntity =
                        gkVisitPhotoEntityMapper.selectByPrimaryKey(item.getPhotoId());
                if (gkVisitPhotoEntity != null) {
                    shelfPhotos.add(gkVisitPhotoEntity.getPhotoKey());
                }
            });
            accountVisitDetailPojo.setShelfPhotos(shelfPhotos);
        }
        List<SaPhotoConncetEntity> stackPhotoList =
                saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_STACK);
        if (stackPhotoList.size() > 0) {
            List<String> stackPhotos = new ArrayList<>();
            stackPhotoList.forEach(item -> {
                GkVisitPhotoEntity gkVisitPhotoEntity =
                        gkVisitPhotoEntityMapper.selectByPrimaryKey(item.getPhotoId());
                if (gkVisitPhotoEntity != null) {
                    stackPhotos.add(gkVisitPhotoEntity.getPhotoKey());
                }
            });
            accountVisitDetailPojo.setStackPhotos(stackPhotos);
        }
        List<SaPhotoConncetEntity> endFramePhotoList =
                saPhotoConncetEntityMapper.selectByForeignIdAndPhotoType(visitId, PhotoTypeContent.VISIT_ENDFRAME);
        if (endFramePhotoList.size() > 0) {
            List<String> endFramePhotos = new ArrayList<>();
            endFramePhotoList.forEach(item -> {
                GkVisitPhotoEntity gkVisitPhotoEntity =
                        gkVisitPhotoEntityMapper.selectByPrimaryKey(item.getPhotoId());
                if (gkVisitPhotoEntity != null) {
                    endFramePhotos.add(gkVisitPhotoEntity.getPhotoKey());
                }
            });
            accountVisitDetailPojo.setEndFramePhotos(endFramePhotos);
        }

        // 设置基础信息
        SaUserInformationEntity saUserInformationEntity =
                saUserInfoMapper.selectByPositionIdAndEmpId(gkVisitEntity.getPositionId(), gkVisitEntity.getEmpId());
        Map<String, Object> crmInfo = saUserInfoMapper.selectCrmUserInfoByPhone(saUserInformationEntity.getPhone());
        Map<String, String> positionInfo =
                saUserInfoMapper.selectUserPositionInformationByRowId(saUserInformationEntity.getRowId());
        accountVisitDetailPojo.setId(gkAccountEntity.getId());
        accountVisitDetailPojo.setAccountId(gkVisitEntity.getAccountId());
        accountVisitDetailPojo.setAccountName(gkAccountEntity.getName());
        accountVisitDetailPojo.setAccountAddress(gkAccountEntity.getAddress());
        accountVisitDetailPojo.setEmpName(String.valueOf(crmInfo.get("name")));
        accountVisitDetailPojo.setEmpPosition(positionInfo.get("name"));
        accountVisitDetailPojo.setLocationAddr(gkVisitEntity.getLocationAddr());

        // 设置描述
        SaVisitContentEntity saVisitContentEntity =
                saVisitContentEntityMapper.selectFirstByGkVisitId(visitId);
        if (saVisitContentEntity != null) {
            accountVisitDetailPojo.setShelfContent(saVisitContentEntity.getShelfContent());
            accountVisitDetailPojo.setStackContent(saVisitContentEntity.getStackContent());
            accountVisitDetailPojo.setEndFrameContent(saVisitContentEntity.getEndFrameContent());
        }

        // 设置竞品
        List<Map<String, Object>> competitors = this.getVisitCompetitorList(visitId);
        if (competitors.size() > 0) {
            List<CompetitorPojo> competitorPojoList = new ArrayList<>();
            competitors.forEach(item -> {
                CompetitorPojo competitorPojo =
                        this.getCompetitorDetail(String.valueOf(item.get("competitorId")), visitId);
                competitorPojoList.add(competitorPojo);
            });
            accountVisitDetailPojo.setCompetitors(competitorPojoList);
        }

        // 设置V码列表
        List<Map<String, Object>> vCodeMapList = this.getListForOneVisit(visitId, gkVisitEntity.getAccountId());
        if (vCodeMapList.size() > 0) {
            List<VCodePojo> vCodePojoList = new ArrayList<>();
            vCodeMapList.forEach(item -> {
                VCodePojo vCodePojo = new VCodePojo();
                vCodePojo.setId(String.valueOf(item.get("vCode")));
                vCodePojo.setCategory(String.valueOf(item.get("category")));
                vCodePojo.setOrder(Integer.parseInt(String.valueOf(item.get("orderQty"))));
                vCodePojo.setPrice(Integer.parseInt(String.valueOf(item.get("price")).split("\\.")[0]));
                vCodePojo.setStock(Integer.parseInt(String.valueOf(item.get("stockQty"))));
                vCodePojoList.add(vCodePojo);
            });
            accountVisitDetailPojo.setVCodes(vCodePojoList);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = gkVisitEntity.getVisitDate();
        accountVisitDetailPojo.setVisitDate(dateFormat.format(date));
        return accountVisitDetailPojo;
    }

    /**
     * 查看拜访取消原因
     *
     * @param visitId 拜访ID
     * @return 返回该拜访的取消原因，如果该拜访未被取消或查询失败则返回<strong>null</strong>
     */
    @Override
    public Map<String, Object> getCancelReason(String visitId) {
        Map<String, Object> result = gkVisitEntityMapper.selectCancelReasonByVisitId(visitId);
        if (result == null) {
            return null;
        }
        return result;
    }

    /**
     * 根据门店Id查询今天是否存在拜访任务
     *
     * @param accountId 门店Id
     * @return true：该门店今天已存在拜访任务；false：该门店今天不存在拜访任务
     */
    @Override
    public boolean checkVisitMissionByAccountId(String accountId) {
        int count = gkVisitEntityMapper.checkVisitMissionByAccountId(accountId);
        if (count > 0) {
            return true;
        }
        return false;
    }
}