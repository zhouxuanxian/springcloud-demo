package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkCompetitorEntity;
public interface GkCompetitorService{


    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorEntity record);

    int insertSelective(GkCompetitorEntity record);

    GkCompetitorEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorEntity record);

    int updateByPrimaryKey(GkCompetitorEntity record);

}
