package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkDailyPhotoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GkDailyPhotoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkDailyPhotoEntity record);

    int insertSelective(GkDailyPhotoEntity record);

    GkDailyPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDailyPhotoEntity record);

    int updateByPrimaryKey(GkDailyPhotoEntity record);

    /**
     * 根据日报ID获取该日报下的图片列表
     *
     * @param dailyId 要查询的日报ID
     * @return 该日报下的所有图片
     */
    List<GkDailyPhotoEntity> selectByDailyId(@Param("id") String dailyId);


}