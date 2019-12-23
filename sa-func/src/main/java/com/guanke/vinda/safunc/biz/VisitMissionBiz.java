package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;
import com.guanke.vinda.samodels.model.pojo.visit.AccountVisitDetailPojo;
import com.guanke.vinda.samodels.model.pojo.visit.CompetitorPojo;
import com.guanke.vinda.samodels.model.pojo.visit.NonAccountVisitDetailPojo;
import com.guanke.vinda.samodels.model.pojo.visit.SubmitVisitPojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 门店拜访相关业务接口
 *
 * @author Nicemorning
 */
public interface VisitMissionBiz {
    /**
     * 通过员工的职位ID和员工ID，查询该员工在指定月份下的任务统计数据
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       指定查询日期
     * @return 返回该月份所有的任务统计数据
     */
    List<Map<String, Object>> getMonthVisitMissionByEmpIdAndPositionIdAndMonth(String empId,
                                                                               String positionId,
                                                                               String date);


    /**
     * 通过员工的职位ID和员工ID，查询该员工指定日期下的所有任务列表
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param page       页码
     * @param pageSize   页容量
     * @param date       指定查询日期
     * @return 返回指定日期下的所有任务列表
     */
    Page<Map<String, Object>> getMissionListByDateAndPositionIdAndEmpId(String positionId,
                                                                        String empId,
                                                                        String date,
                                                                        int page,
                                                                        int pageSize);

    /**
     * 新建拜访事项，如果是非拜访事项，则accountIds传入null即可
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param visitDate  拜访时间
     * @param accountIds 客户ID列表
     * @return 如果新建成功返回true，否则返回false
     */
    boolean insertAccountVisitEntity(String empId, String positionId,
                                     Date visitDate, List<String> accountIds);


    /**
     * 通过拜访ID和员工ID获取该拜访的详情
     *
     * @param visitId 拜访ID
     * @param empId   员工ID
     * @return 返回拜访详情
     */
    Map<String, Object> getVisitByVisitIdAndEmpId(String visitId, String empId);

    /**
     * 通过职位ID和筛选条件获取该职位下的GT门店列表
     *
     * @param positionId 职位ID
     * @param params     筛选参数
     * @param page       页码
     * @param pageSize   页容量
     * @return 返回符合条件的GT门店列表
     */
    Page<Map<String, Object>> getGtShopList(String positionId, Map<String, Object> params, int page, int pageSize);

    /**
     * 提交拜访
     *
     * @param visitPojo  拜访参数
     * @param positionId 操作人的职位ID
     * @param empId      员工ID
     * @return 如果提交成功返回<strong>"success"</strong>，反之返回相应的错误信息
     */
    String submitVisit(SubmitVisitPojo visitPojo, String positionId, String empId);

    /**
     * 取消拜访
     *
     * @param params 取消参数
     * @return 如果成功则返回<strong>"success"</strong>，反之将返回相应的错误信息
     */
    String cancelVisit(Map<String, String> params);

    /**
     * 删除拜访
     *
     * @param id 拜访ID
     * @return 如果成功则返回<strong>"success"</strong>，反之将返回相应的错误信息
     */
    String deleteVisit(String id);


    /**
     * 保存拜访图片
     *
     * @param accountName 门店名
     * @param type        类型
     * @param photoIds    图片数组
     * @return 如果保存成功返回所有图片对应的七牛云外链，如果包含上传失败的内容，则该图片外链将为"null"
     */
    Map<String, Object> saveVisitPhoto(String accountName, String type, List<String> photoIds);

    /**
     * 通过竞品ID和拜访ID获取竞品详情
     *
     * @param competitorId 竞品ID
     * @param visitId      拜访ID
     * @return 竞品详情
     */
    CompetitorPojo getCompetitorDetail(String competitorId, String visitId);

    /**
     * 更新竞品信息
     *
     * @param params 参数
     * @return 如果更新成功则返回true，反之返回false
     */
    boolean updateCompetitor(Map<String, Object> params);

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
    boolean saveCompetitor(String empId,
                           String positionId,
                           String accountId,
                           String visitId,
                           List<CompetitorPojo> competitors);

    /**
     * 通过拜访ID获取该拜访下的竞品列表
     *
     * @param visitId 拜访ID
     * @return 返回该拜访下的所有竞品列表
     */
    List<Map<String, Object>> getVisitCompetitorList(String visitId);

    /**
     * 通过拜访和门店ID获取符合条件的V码、库存等信息
     *
     * @param visitId   拜访ID
     * @param accountId 门店ID
     * @return 返回符合条件的信息
     */
    List<Map<String, Object>> getListForOneVisit(String visitId, String accountId);

    /**
     * 通过相关参数查询该拜访下的V码列表
     *
     * @param params 查询参数
     * @return 返回符合条件的V码列表
     */
    List<Map<String, Object>> loadCommonVCodeList(Map<String, Object> params);

    /**
     * 通过关键字和默认V码分页查询V码列表
     *
     * @param page     页码
     * @param pageSize 页容量
     * @param params   相关参数
     * @return 返回符合条件的分页结果
     */
    Page<Map<String, Object>> getVCodeList(int page, int pageSize, Map<String, Object> params);

    /**
     * 保存V码列表
     *
     * @param params 保存参数
     * @return 如果保存成功则返回true，反之返回false
     */
    boolean saveAccountVCode(Map<String, Object> params);

    /**
     * 获取已完成的拜访基础数据及对比
     *
     * @param empId   员工ID
     * @param visitId 拜访ID
     * @return 该拜访的基本信息和数据
     */
    Map<String, Object> getVisitAndCompare(String empId, String visitId);

    /**
     * 通过拜访ID获取该拜访下的图片列表
     *
     * @param visitId 拜访ID
     * @return 该拜访下的图片列表
     */
    List<Map<String, Object>> getVisitPhotos(String visitId);


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
    Page<Map<String, Object>> getVisitMissionByCondition(String accountId,
                                                         String empId,
                                                         String positionId,
                                                         String visitDate,
                                                         int pageNum,
                                                         int pageSize);

    /**
     * 通过拜访ID获取非拜访事项详情
     *
     * @param visitId 拜访ID
     * @return 获取非拜访事项详情
     */
    NonAccountVisitDetailPojo getNonAccountVisitDetail(String visitId);

    /**
     * 通过拜访ID获取门店拜访事项详情
     *
     * @param visitId 拜访ID
     * @return 获取门店拜访事项详情
     */
    AccountVisitDetailPojo getRealAccountVisitDetail(String visitId);

    /**
     * 查看拜访取消原因
     *
     * @param visitId 拜访ID
     * @return 返回该拜访的取消原因，如果该拜访未被取消或查询失败则返回<strong>null</strong>
     */
    Map<String, Object> getCancelReason(String visitId);

    /**
     * 根据门店Id查询今天是否存在拜访任务
     *
     * @param accountId 门店Id
     * @return true：该门店今天已存在拜访任务；false：该门店今天不存在拜访任务
     */
    boolean checkVisitMissionByAccountId(String accountId);
}
