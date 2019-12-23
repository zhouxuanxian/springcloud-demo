package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkCompetitorPhotoEntity;
public interface GkCompetitorPhotoService{


    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorPhotoEntity record);

    int insertSelective(GkCompetitorPhotoEntity record);

    GkCompetitorPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorPhotoEntity record);

    int updateByPrimaryKey(GkCompetitorPhotoEntity record);

}
