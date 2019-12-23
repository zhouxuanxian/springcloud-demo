package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkVisitEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkVisitEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkVisitEntity record);

    int insertSelective(GkVisitEntity record);

    GkVisitEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitEntity record);

    int updateByPrimaryKey(GkVisitEntity record);

    /**
     * 通过用户ID和职位ID查询该用户在指定月份下的拜访任务统计
     *
     * @param start      起始日期
     * @param end        结束日期
     * @param empId      用户ID
     * @param positionId 职位ID
     * @return 返回该用户的所有任务信息
     */
    List<Map<String, Object>> selectMonthMissionByEmpIdAndPositionIdAndStartDayAndEndDay(@Param("start") String start,
                                                                                         @Param("end") String end,
                                                                                         @Param("empId") String empId,
                                                                                         @Param("positionId") String positionId);

    /**
     * 通过用户ID和职位ID查询该用户在指定日期下的拜访任务列表
     *
     * @param empId      员工ID
     * @param positionId 职位ID
     * @param date       要查询的日期
     * @return 返回该员工在指定日期下的所有拜访任务
     */
    List<Map<String, Object>> selectMissionListByDateAndPositionIdAndEmpId(@Param("empId") String empId,
                                                                           @Param("positionId") String positionId,
                                                                           @Param("date") String date);

    List<String> selectAllCancelVisitIdByAccountIdAndEmpIdAndPositionId(@Param("accountId") String accountId,
                                                                        @Param("empId") String empId,
                                                                        @Param("positionId") String positionId);

    Map<String, Object> selectVisitByIdAndEmpId(@Param("id") String id);

    Map<String, Object> selectVisitPuroseValueByCode(@Param("code") String code);

    List<Map<String, Object>> selectPhotosByVisitId(@Param("id") String id);

    List<Map<String, Object>> selectGtShopByPositionIdAndQueryParams(@Param("positionId") String positionId,
                                                                     @Param("keyword") String keyword,
                                                                     @Param("interval") int interval,
                                                                     @Param("district") String district,
                                                                     @Param("level") String level);

    Map<String, Object> selectVisitBaseInfoByAccountIdAndVisitDate(@Param("accountId") String accountId,
                                                                   @Param("visitDate") String visitDate);


    String selectNextNeedVisitShopNameByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                  @Param("empId") String empId,
                                                                  @Param("date") String date);

    /**
     * 获取当日拜访任务数据统计，<br/>
     * 其中<strong>type</strong>为0时，为总拜访任务数；<br/>
     * 当type为1时，为总非拜访事项数；<br/>
     * 当type为2时，为总拜访完成数
     *
     * @param positionId 职位ID
     * @param empId      员工ID
     * @param type       数据类型
     * @param date       当日日期
     * @return 符合条件的统计数据
     */
    int selectTodayTotalVisitMissionCountByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                     @Param("empId") String empId,
                                                                     @Param("type") int type,
                                                                     @Param("date") String date);

    List<Map<String, Object>> selectVisitMissionByCondition(@Param("accountId") String accountId,
                                                            @Param("empId") String empId,
                                                            @Param("positionId") String positionId,
                                                            @Param("visitDate") String visitDate);

    List<Map<String, Object>> getVisitMissionHistoryListByConditionForDaily(@Param("empId") String empId,
                                                                            @Param("positionId") String positionId,
                                                                            @Param("date") String date);

    List<Map<String, Object>> selectVisitPhotosListByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                               @Param("empId") String empId,
                                                                               @Param("date") String date);

    Map<String, Object> selectCompareByAccountIdAndDate(@Param("accountId") String accountId,
                                                        @Param("date") String date);

    Map<String, Object> selectCancelReasonByVisitId(@Param("visitId") String visitId);

    List<Map<String, Object>> selectMaintainAccountByEmpIdAndDate(@Param("empId") String empId,
                                                                  @Param("date") String date);

    int checkVisitMissionByAccountId(@Param("accountId") String accountId);
}