package com.guanke.vinda.sasync.mapper;

import com.guanke.vinda.samodels.model.entity.GkGtReimTempEntity;

public interface GkGtReimTempEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkGtReimTempEntity record);

    int insertSelective(GkGtReimTempEntity record);

    GkGtReimTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkGtReimTempEntity record);

    int updateByPrimaryKey(GkGtReimTempEntity record);

    GkGtReimTempEntity selectByReimId(String reimId);



}