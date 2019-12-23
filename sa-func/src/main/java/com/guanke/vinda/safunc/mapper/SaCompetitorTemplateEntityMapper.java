package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaCompetitorTemplateEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SaCompetitorTemplateEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaCompetitorTemplateEntity record);

    int insertOrUpdate(SaCompetitorTemplateEntity record);

    int insertOrUpdateSelective(SaCompetitorTemplateEntity record);

    int insertSelective(SaCompetitorTemplateEntity record);

    SaCompetitorTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaCompetitorTemplateEntity record);

    int updateByPrimaryKey(SaCompetitorTemplateEntity record);

    int updateBatch(List<SaCompetitorTemplateEntity> list);

    int batchInsert(@Param("list") List<SaCompetitorTemplateEntity> list);

    int deleteByVisitId(@Param("visitId") String visitId);

    int updateDeleteFlagByVisitId(@Param("updatedDeleteFlag") Integer updatedDeleteFlag, @Param("visitId") String visitId);

    List<Map<String, Object>> selectByVisitId(@Param("visitId")String visitId);


}