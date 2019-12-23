package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkListOfValueEntity;
public interface GkListOfValueEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkListOfValueEntity record);

    int insertSelective(GkListOfValueEntity record);

    GkListOfValueEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkListOfValueEntity record);

    int updateByPrimaryKey(GkListOfValueEntity record);

}
