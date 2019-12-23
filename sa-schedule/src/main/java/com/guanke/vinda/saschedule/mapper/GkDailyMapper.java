package com.guanke.vinda.saschedule.mapper;

import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface GkDailyMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkDailyEntity record);

    int insertSelective(GkDailyEntity record);

    GkDailyEntity selectByPrimaryKey(String id);

    List<Map<String, Object>> selectByPositionAndEmpIdSortByUpdateTime(@Param("positionId") String positionId,
                                                                       @Param("empId") String empId);

    int updateByPrimaryKeySelective(GkDailyEntity record);

    int updateByPrimaryKey(GkDailyEntity record);

    Map<String, Integer> selectDailyCommentAndLikesCountByPositionIdAndEmpIdAfterDate(@Param("positionId") String positionId,
                                                                                      @Param("empId") String empId);

    Map<String, Integer> selectDailyCommentAndLikesCountDailyByPositionIdAndEmpId(@Param("positionId") String positionId,
                                                                                  @Param("empId") String empId);

    List<Map<String, Object>> selectVisitClientByEmpIdAndPositionIdAndDate(@Param("empId") String empId,
                                                                           @Param("positionId") String positionId,
                                                                           @Param("date") Date date);

    List<Map<String, Object>> selectCommentByDailyId(@Param("dailyId") String id);

    Integer selectCountByPositionIdAndEmpIdAndDraftDate(@Param("positionId") String positionId,
                                                        @Param("empId") String empId,
                                                        @Param("dailyDate") String dailyDate);

    String selectDraftIdByPositionIdAndEmpIdAndDraftDate(@Param("positionId") String positionId,
                                                         @Param("empId") String empId,
                                                         @Param("dailyDate") String dailyDate);

    List<Map<String, Object>> selectalldailycommentmessagebypositionidandempId(@Param("positionId") String positionId,
                                                                               @Param("empId") String empId);

    List<Map<String, Object>> selectAllDailyLikeMessageByPositionIdAndEmpId(@Param("positionId") String positionId,
                                                                            @Param("empId") String empId);


}