package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;
import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
import com.guanke.vinda.samodels.model.pojo.daily.DailySubmitPojo;

import java.util.List;
import java.util.Map;

/**
 * 日报相关业务逻辑
 *
 * @author Nicemorning
 */
public interface DailyBiz {
    /**
     * 通过职位ID，员工ID获取该员工名下的所有日报，返回分页结果
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param pageNum    页码
     * @param pageSize   页容量
     * @return 符合条件的所有日报的分页结果
     */
    Page<Map<String, Object>> getMyDailys(String positionId, String empId, int pageNum, int pageSize);

    /**
     * 通过职位ID，员工ID获取该用户最新的日报消息
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回最新的点赞数和评论数
     */
    Map<String, Integer> dailyMessage(String positionId, String empId);

    /**
     * 通过职位ID，员工ID获取该用户今日的日报消息
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回今日的点赞数和评论数
     */
    Map<String, Integer> dailyMessageToday(String positionId, String empId);

    /**
     * 保存日报
     *
     * @param option     操作类型，如果为0则保存为草稿，如果为1则为提交日报
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param daily      日报内容
     * @return 返回日报实体
     */
    GkDailyEntity saveDaily(int option, String empId, String positionId, DailySubmitPojo daily);

    /**
     * 根据日报的ID获取该日报详情
     *
     * @param id 日报ID
     * @return 返回日报实体
     */
    Map<String, Object> getDailyDetail(String id);


    /**
     * 通过当前用户的职位ID，员工ID和当前日期判断该用户所处职位下今天是否已经提交过日报
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param dailyDate  当日日期
     * @return 如果已经提交过，则返回true；反之返回false
     */
    boolean isCommittedByPositionIdAndEmpIdAndDailyDate(String positionId,
                                                        String empId,
                                                        String dailyDate);

    /**
     * 通过当前用户的职位ID，员工ID和当前日期判断该用户所处职位下今天是否已经保存过日报草稿
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param dailyDate  当日日期
     * @return 如果已经保存过草稿，则返回该草稿的ID；反之返回null
     */
    String getDraftIdByPositionIdAndEmpIdAndDailyDate(String positionId,
                                                      String empId,
                                                      String dailyDate);

    /**
     * 根据手机号更新该用户的日报消息记录查看时间
     *
     * @param phone 用户的手机号
     */
    int updateMessageCheck(String phone);

    /**
     * 通过员工ID和职位ID获取该员工的所有日报消息提醒列表
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @return 返回该员工ID和职位ID下的所有日报消息提醒
     */
    List<Map<String, Object>> getDailyMessageByPositionIdAndEmpId(String positionId, String empId);

    /**
     * 通过日报ID获取该日报下的图片
     *
     * @param id 日报ID
     * @return 返回该日报下的所有图片
     */
    List<String> getDailyImages(String id);

    /**
     * 通过员工ID和职位ID获取日报提交时顶部的初始数据
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       要查询的日期
     * @return 返回符合条件的日报提交初始数据
     */
    Map<String, Object> getVisitMissionForDaily(String empId, String positionId, String date);

    /**
     * 通过员工ID、职位ID和日期查询指定日期的竞品记录
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param date       指定日期
     * @return 返回符合条件的竞品记录
     */
    List<Map<String, Object>> getCompetitorListByPositionIdAndEmpIdAndDate(String positionId,
                                                                           String empId,
                                                                           String date);

    /**
     * 通过员工ID、职位ID和日期查询指定日期的拍照记录
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       指定日期
     * @return 返回符合条件的拍照记录
     */
    List<Map<String, Object>> getUploadPhotosListByEmpIdAndPositionIdAndDate(String empId,
                                                                             String positionId,
                                                                             String date);

    /**
     * 通过员工ID和日期查询指定日期的门店维护记录
     *
     * @param empId 员工ID
     * @param date  查询日期
     * @return 返回符合条件的门店维护记录
     */
    List<Map<String, Object>> getMaintainAccountListByEmpIdAndDate(String empId, String date);
}
