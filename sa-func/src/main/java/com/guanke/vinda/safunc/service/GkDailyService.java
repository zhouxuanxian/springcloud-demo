package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
public interface GkDailyService{


    int deleteByPrimaryKey(String id);

    int insert(GkDailyEntity record);

    int insertSelective(GkDailyEntity record);

    GkDailyEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDailyEntity record);

    int updateByPrimaryKey(GkDailyEntity record);

}
