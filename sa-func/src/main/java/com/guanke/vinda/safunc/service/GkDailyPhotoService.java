package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkDailyPhotoEntity;
public interface GkDailyPhotoService{


    int deleteByPrimaryKey(String id);

    int insert(GkDailyPhotoEntity record);

    int insertSelective(GkDailyPhotoEntity record);

    GkDailyPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDailyPhotoEntity record);

    int updateByPrimaryKey(GkDailyPhotoEntity record);

}
