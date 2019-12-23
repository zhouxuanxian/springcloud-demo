package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaCompetitorPhotoTempEntity;

public interface SaCompetitorPhotoTempEntityService {


    int deleteByPrimaryKey(String id);

    int insert(SaCompetitorPhotoTempEntity record);

    int insertOrUpdate(SaCompetitorPhotoTempEntity record);

    int insertOrUpdateSelective(SaCompetitorPhotoTempEntity record);

    int insertSelective(SaCompetitorPhotoTempEntity record);

    SaCompetitorPhotoTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaCompetitorPhotoTempEntity record);

    int updateByPrimaryKey(SaCompetitorPhotoTempEntity record);

    int updateBatch(List<SaCompetitorPhotoTempEntity> list);

    int batchInsert(List<SaCompetitorPhotoTempEntity> list);

}


