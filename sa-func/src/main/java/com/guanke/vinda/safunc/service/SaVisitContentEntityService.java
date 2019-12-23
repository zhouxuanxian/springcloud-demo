package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitContentEntity;
public interface SaVisitContentEntityService{


    int deleteByPrimaryKey(String id);

    int insert(SaVisitContentEntity record);

    int insertOrUpdate(SaVisitContentEntity record);

    int insertOrUpdateSelective(SaVisitContentEntity record);

    int insertSelective(SaVisitContentEntity record);

    SaVisitContentEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitContentEntity record);

    int updateByPrimaryKey(SaVisitContentEntity record);

    int updateBatch(List<SaVisitContentEntity> list);

    int batchInsert(List<SaVisitContentEntity> list);

}
