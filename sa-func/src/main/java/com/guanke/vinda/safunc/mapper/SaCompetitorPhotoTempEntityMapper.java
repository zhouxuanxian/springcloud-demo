package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaCompetitorPhotoTempEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaCompetitorPhotoTempEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaCompetitorPhotoTempEntity record);

    int insertOrUpdate(SaCompetitorPhotoTempEntity record);

    int insertOrUpdateSelective(SaCompetitorPhotoTempEntity record);

    int insertSelective(SaCompetitorPhotoTempEntity record);

    SaCompetitorPhotoTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaCompetitorPhotoTempEntity record);

    int updateByPrimaryKey(SaCompetitorPhotoTempEntity record);

    int updateBatch(List<SaCompetitorPhotoTempEntity> list);

    int batchInsert(@Param("list") List<SaCompetitorPhotoTempEntity> list);
}