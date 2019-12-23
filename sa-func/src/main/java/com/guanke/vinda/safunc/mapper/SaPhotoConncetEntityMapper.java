package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaPhotoConncetEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaPhotoConncetEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaPhotoConncetEntity record);

    int insertOrUpdate(SaPhotoConncetEntity record);

    int insertOrUpdateSelective(SaPhotoConncetEntity record);

    int insertSelective(SaPhotoConncetEntity record);

    SaPhotoConncetEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaPhotoConncetEntity record);

    int updateByPrimaryKey(SaPhotoConncetEntity record);

    int updateBatch(List<SaPhotoConncetEntity> list);

    int batchInsert(@Param("list") List<SaPhotoConncetEntity> list);

    List<SaPhotoConncetEntity> selectByForeignIdAndPhotoType(@Param("foreignId")String foreignId,@Param("photoType")String photoType);

    int deleteByForeignId(@Param("foreignId")String foreignId);


}