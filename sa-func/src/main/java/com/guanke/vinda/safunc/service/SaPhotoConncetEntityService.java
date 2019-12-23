package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.SaPhotoConncetEntity;
import java.util.List;

public interface SaPhotoConncetEntityService {


    int deleteByPrimaryKey(String id);

    int insert(SaPhotoConncetEntity record);

    int insertSelective(SaPhotoConncetEntity record);

    SaPhotoConncetEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaPhotoConncetEntity record);

    int updateByPrimaryKey(SaPhotoConncetEntity record);

    int updateBatch(List<SaPhotoConncetEntity> list);

    int batchInsert(List<SaPhotoConncetEntity> list);

    int insertOrUpdate(SaPhotoConncetEntity record);

    int insertOrUpdateSelective(SaPhotoConncetEntity record);
}


