package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.safunc.mapper.SaCompetitorPhotoTempEntityMapper;
import com.guanke.vinda.samodels.model.entity.SaCompetitorPhotoTempEntity;
import com.guanke.vinda.safunc.service.SaCompetitorPhotoTempEntityService;

@Service
public class SaCompetitorPhotoTempEntityServiceImpl implements SaCompetitorPhotoTempEntityService {

    @Resource
    private SaCompetitorPhotoTempEntityMapper saCompetitorPhotoTempEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saCompetitorPhotoTempEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.insertSelective(record);
    }

    @Override
    public SaCompetitorPhotoTempEntity selectByPrimaryKey(String id) {
        return saCompetitorPhotoTempEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaCompetitorPhotoTempEntity record) {
        return saCompetitorPhotoTempEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaCompetitorPhotoTempEntity> list) {
        return saCompetitorPhotoTempEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaCompetitorPhotoTempEntity> list) {
        return saCompetitorPhotoTempEntityMapper.batchInsert(list);
    }

}


