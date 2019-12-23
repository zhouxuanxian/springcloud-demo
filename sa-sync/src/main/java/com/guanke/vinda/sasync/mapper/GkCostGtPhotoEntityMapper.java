package com.guanke.vinda.sasync.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.guanke.vinda.samodels.model.entity.GkCostGtPhotoEntity;

public interface GkCostGtPhotoEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCostGtPhotoEntity record);

    int insertSelective(GkCostGtPhotoEntity record);

    GkCostGtPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCostGtPhotoEntity record);

    int updateByPrimaryKey(GkCostGtPhotoEntity record);

    List<GkCostGtPhotoEntity> selectByRowId(@Param("rowId")String rowId,@Param("type") String type);


}