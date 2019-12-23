package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkAccountCompetitorEntity;
public interface GkAccountCompetitorEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkAccountCompetitorEntity record);

    int insertSelective(GkAccountCompetitorEntity record);

    GkAccountCompetitorEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountCompetitorEntity record);

    int updateByPrimaryKey(GkAccountCompetitorEntity record);

}
