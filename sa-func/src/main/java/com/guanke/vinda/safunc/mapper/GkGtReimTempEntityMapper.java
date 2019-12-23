package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkGtReimTempEntity;

import java.util.Map;

public interface GkGtReimTempEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkGtReimTempEntity record);

    int insertSelective(GkGtReimTempEntity record);

    GkGtReimTempEntity selectByPrimaryKey(String id);

    Map<String,Object> selectTempListByRowId(String rowId);

    int updateByPrimaryKeySelective(GkGtReimTempEntity record);

    int updateByPrimaryKey(GkGtReimTempEntity record);
}