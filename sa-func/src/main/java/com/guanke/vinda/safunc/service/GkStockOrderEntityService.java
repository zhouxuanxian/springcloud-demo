package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkStockOrderEntity;
public interface GkStockOrderEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkStockOrderEntity record);

    int insertSelective(GkStockOrderEntity record);

    GkStockOrderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkStockOrderEntity record);

    int updateByPrimaryKey(GkStockOrderEntity record);

}
