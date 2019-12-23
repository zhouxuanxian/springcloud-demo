package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaCompetitorTemplateEntity;

public interface SaCompetitorTemplateEntityService {


    int deleteByPrimaryKey(String id);

    int insert(SaCompetitorTemplateEntity record);

    int insertOrUpdate(SaCompetitorTemplateEntity record);

    int insertOrUpdateSelective(SaCompetitorTemplateEntity record);

    int insertSelective(SaCompetitorTemplateEntity record);

    SaCompetitorTemplateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaCompetitorTemplateEntity record);

    int updateByPrimaryKey(SaCompetitorTemplateEntity record);

    int updateBatch(List<SaCompetitorTemplateEntity> list);

    int batchInsert(List<SaCompetitorTemplateEntity> list);

}






