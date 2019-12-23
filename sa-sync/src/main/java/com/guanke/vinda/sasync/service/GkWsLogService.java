package com.guanke.vinda.sasync.service;

import com.guanke.vinda.samodels.model.entity.GkWsLogEntity;

public interface GkWsLogService {


    int deleteByPrimaryKey(String id);

    int insert(GkWsLogEntity record);

    int insertSelective(GkWsLogEntity record);

    GkWsLogEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkWsLogEntity record);

    int updateByPrimaryKey(GkWsLogEntity record);

}
