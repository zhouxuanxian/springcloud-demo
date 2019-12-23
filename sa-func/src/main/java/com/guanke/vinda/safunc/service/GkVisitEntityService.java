package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkVisitEntity;
public interface GkVisitEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkVisitEntity record);

    int insertSelective(GkVisitEntity record);

    GkVisitEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitEntity record);

    int updateByPrimaryKey(GkVisitEntity record);

}
