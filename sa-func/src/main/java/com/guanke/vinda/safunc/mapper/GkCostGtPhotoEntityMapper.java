package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkCostGtPhotoEntity;

import java.util.List;
import java.util.Map;

public interface GkCostGtPhotoEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCostGtPhotoEntity record);

    int insertSelective(GkCostGtPhotoEntity record);

    GkCostGtPhotoEntity selectByPrimaryKey(String id);

    List<Map<String,Object>> selectByRowId(String id);
    List<Map<String,Object>> selectPhotoByRowId(String id);

    int updateByPrimaryKeySelective(GkCostGtPhotoEntity record);

    int updateByPrimaryKey(GkCostGtPhotoEntity record);
}