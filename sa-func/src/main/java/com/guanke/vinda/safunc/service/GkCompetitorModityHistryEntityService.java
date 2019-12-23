package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkCompetitorModityHistryEntity;
import java.util.List;

public interface GkCompetitorModityHistryEntityService {


    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorModityHistryEntity record);

    int insertOrUpdate(GkCompetitorModityHistryEntity record);

    int insertOrUpdateSelective(GkCompetitorModityHistryEntity record);

    int insertSelective(GkCompetitorModityHistryEntity record);

    GkCompetitorModityHistryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorModityHistryEntity record);

    int updateByPrimaryKey(GkCompetitorModityHistryEntity record);

    int updateBatch(List<GkCompetitorModityHistryEntity> list);

    int batchInsert(List<GkCompetitorModityHistryEntity> list);

}


