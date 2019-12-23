package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitExtEntity;

public interface SaVisitExtEntityService {


    int deleteByPrimaryKey(String id);

    int insert(SaVisitExtEntity record);

    int insertOrUpdate(SaVisitExtEntity record);

    int insertOrUpdateSelective(SaVisitExtEntity record);

    int insertSelective(SaVisitExtEntity record);

    SaVisitExtEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitExtEntity record);

    int updateByPrimaryKey(SaVisitExtEntity record);

    int updateBatch(List<SaVisitExtEntity> list);

    int batchInsert(List<SaVisitExtEntity> list);

}


