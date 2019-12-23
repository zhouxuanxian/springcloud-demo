package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkCompetitorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkCompetitorMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorEntity record);

    int insertSelective(GkCompetitorEntity record);

    GkCompetitorEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorEntity record);

    int updateByPrimaryKey(GkCompetitorEntity record);

    int selectCountByDate(@Param("positionId") String positionId,
                          @Param("empId") String empId,
                          @Param("date") String date);

    List<Map<String, Object>> selectCompetitorPhotosByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                                @Param("empId") String empId,
                                                                                @Param("date") String date);

    List<Map<String, Object>> selectCompetitorListByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                              @Param("empId") String empId,
                                                                              @Param("date") String date);

    GkCompetitorEntity selectFirstByBarCodeNumber(@Param("barCodeNumber")String barCodeNumber);



    List<Map<String, Object>> selectCompetitorByCondition(@Param("accountId") String accountId,
                                                          @Param("empId") String empId,
                                                          @Param("positionId") String positionId,
                                                          @Param("photoPrefix") String photoPrefix);
}