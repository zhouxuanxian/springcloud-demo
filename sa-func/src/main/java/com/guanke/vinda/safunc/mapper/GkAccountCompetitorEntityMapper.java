package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountCompetitorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkAccountCompetitorEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkAccountCompetitorEntity record);

    int insertSelective(GkAccountCompetitorEntity record);

    GkAccountCompetitorEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountCompetitorEntity record);

    int updateByPrimaryKey(GkAccountCompetitorEntity record);

    List<GkAccountCompetitorEntity> selectByAccountIdAndBarCodeNumberAndVisitIdAndCompetitorId(@Param("accountId") String accountId, @Param("barCodeNumber") String barCodeNumber, @Param("visitId") String visitId, @Param("competitorId") String competitorId);

    List<Map<String, Object>> selectVisitCompetitorListByVisitId(@Param("visitId") String visitId);

    List<Map<String, Object>> selectAllCompetitorListByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                                 @Param("empId") String empId,
                                                                                 @Param("date") String date);

    GkAccountCompetitorEntity selectFirstByCompetitorIdAndVisitId(@Param("competitorId")String competitorId,@Param("visitId")String visitId);


}