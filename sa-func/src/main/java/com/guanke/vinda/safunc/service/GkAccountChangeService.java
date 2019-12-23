package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkAccountChangeEntity;

public interface GkAccountChangeService {


    int deleteByPrimaryKey(String id);

    int insert(GkAccountChangeEntity record);

    int insertSelective(GkAccountChangeEntity record);

    GkAccountChangeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountChangeEntity record);

    int updateByPrimaryKey(GkAccountChangeEntity record);

}



