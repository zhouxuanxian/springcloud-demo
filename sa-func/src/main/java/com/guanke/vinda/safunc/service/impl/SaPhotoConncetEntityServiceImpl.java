package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.SaPhotoConncetEntity;
import java.util.List;
import com.guanke.vinda.safunc.mapper.SaPhotoConncetEntityMapper;
import com.guanke.vinda.safunc.service.SaPhotoConncetEntityService;

@Service
public class SaPhotoConncetEntityServiceImpl implements SaPhotoConncetEntityService {

    @Resource
    private SaPhotoConncetEntityMapper saPhotoConncetEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saPhotoConncetEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.insertSelective(record);
    }

    @Override
    public SaPhotoConncetEntity selectByPrimaryKey(String id) {
        return saPhotoConncetEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaPhotoConncetEntity> list) {
        return saPhotoConncetEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaPhotoConncetEntity> list) {
        return saPhotoConncetEntityMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaPhotoConncetEntity record) {
        return saPhotoConncetEntityMapper.insertOrUpdateSelective(record);
    }
}


