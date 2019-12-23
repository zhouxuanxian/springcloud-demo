package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkDailyLikeEntity;
public interface GkDailyLikeService{


    int deleteByPrimaryKey(String id);

    int insert(GkDailyLikeEntity record);

    int insertSelective(GkDailyLikeEntity record);

    GkDailyLikeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDailyLikeEntity record);

    int updateByPrimaryKey(GkDailyLikeEntity record);

}
