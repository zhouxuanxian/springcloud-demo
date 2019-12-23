package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkDailyLikeEntity;

public interface GkDailyLikeMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkDailyLikeEntity record);

    int insertSelective(GkDailyLikeEntity record);

    GkDailyLikeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDailyLikeEntity record);

    int updateByPrimaryKey(GkDailyLikeEntity record);
}