package com.guanke.vinda.sasync.mapper;

import com.guanke.vinda.samodels.model.entity.GkWsLogEntity;

public interface GkWsLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkWsLogEntity record);

    int insertSelective(GkWsLogEntity record);

    GkWsLogEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkWsLogEntity record);

    int updateByPrimaryKey(GkWsLogEntity record);
}