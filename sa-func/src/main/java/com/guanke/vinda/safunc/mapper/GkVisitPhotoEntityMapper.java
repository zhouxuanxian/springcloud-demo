package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkVisitPhotoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkVisitPhotoEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkVisitPhotoEntity record);

    int insertSelective(GkVisitPhotoEntity record);

    GkVisitPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkVisitPhotoEntity record);

    int updateByPrimaryKey(GkVisitPhotoEntity record);

    List<GkVisitPhotoEntity> selectByVisitIdOrderByCreatedTime(@Param("visitId")String visitId);

    int deleteByVisitId(@Param("visitId")String visitId);

}