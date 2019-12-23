package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaVisitCompetitorPhotoConnectEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaVisitCompetitorPhotoConnectEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaVisitCompetitorPhotoConnectEntity record);

    int insertOrUpdate(SaVisitCompetitorPhotoConnectEntity record);

    int insertOrUpdateSelective(SaVisitCompetitorPhotoConnectEntity record);

    int insertSelective(SaVisitCompetitorPhotoConnectEntity record);

    SaVisitCompetitorPhotoConnectEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitCompetitorPhotoConnectEntity record);

    int updateByPrimaryKey(SaVisitCompetitorPhotoConnectEntity record);

    int updateBatch(List<SaVisitCompetitorPhotoConnectEntity> list);

    int batchInsert(@Param("list") List<SaVisitCompetitorPhotoConnectEntity> list);

    List<SaVisitCompetitorPhotoConnectEntity> selectByVisitId(@Param("visitId")String visitId);


}