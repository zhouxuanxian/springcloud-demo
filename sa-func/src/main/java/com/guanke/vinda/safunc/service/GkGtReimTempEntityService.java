package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkGtReimTempEntity;
public interface GkGtReimTempEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkGtReimTempEntity record);

    int insertSelective(GkGtReimTempEntity record);

    GkGtReimTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkGtReimTempEntity record);

    int updateByPrimaryKey(GkGtReimTempEntity record);

}
