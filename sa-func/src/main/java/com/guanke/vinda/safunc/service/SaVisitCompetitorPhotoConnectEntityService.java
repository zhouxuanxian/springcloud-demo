package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitCompetitorPhotoConnectEntity;
public interface SaVisitCompetitorPhotoConnectEntityService{


    int deleteByPrimaryKey(String id);

    int insert(SaVisitCompetitorPhotoConnectEntity record);

    int insertOrUpdate(SaVisitCompetitorPhotoConnectEntity record);

    int insertOrUpdateSelective(SaVisitCompetitorPhotoConnectEntity record);

    int insertSelective(SaVisitCompetitorPhotoConnectEntity record);

    SaVisitCompetitorPhotoConnectEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitCompetitorPhotoConnectEntity record);

    int updateByPrimaryKey(SaVisitCompetitorPhotoConnectEntity record);

    int updateBatch(List<SaVisitCompetitorPhotoConnectEntity> list);

    int batchInsert(List<SaVisitCompetitorPhotoConnectEntity> list);

}
