package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkCompetitorModityHistryEntity;

public interface GkCompetitorModityHistryMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorModityHistryEntity record);

    int insertSelective(GkCompetitorModityHistryEntity record);

    GkCompetitorModityHistryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorModityHistryEntity record);

    int updateByPrimaryKey(GkCompetitorModityHistryEntity record);
}