package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkCostGtPhotoEntity;
public interface GkCostGtPhotoEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkCostGtPhotoEntity record);

    int insertSelective(GkCostGtPhotoEntity record);

    GkCostGtPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCostGtPhotoEntity record);

    int updateByPrimaryKey(GkCostGtPhotoEntity record);

}
