package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkAccountEntity;
public interface GkAccountEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkAccountEntity record);

    int insertSelective(GkAccountEntity record);

    GkAccountEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountEntity record);

    int updateByPrimaryKey(GkAccountEntity record);

}
