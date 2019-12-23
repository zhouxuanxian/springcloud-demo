package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkVisitPhotoEntity;
public interface GkVisitPhotoEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkVisitPhotoEntity record);

    int insertSelective(GkVisitPhotoEntity record);

    GkVisitPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitPhotoEntity record);

    int updateByPrimaryKey(GkVisitPhotoEntity record);

}
