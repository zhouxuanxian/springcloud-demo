package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkVisitPhotoEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkVisitPhotoEntity;
import com.guanke.vinda.safunc.service.GkVisitPhotoEntityService;
@Service
public class GkVisitPhotoEntityServiceImpl implements GkVisitPhotoEntityService{

    @Resource
    private GkVisitPhotoEntityMapper gkVisitPhotoEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkVisitPhotoEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkVisitPhotoEntity record) {
        return gkVisitPhotoEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkVisitPhotoEntity record) {
        return gkVisitPhotoEntityMapper.insertSelective(record);
    }

    @Override
    public GkVisitPhotoEntity selectByPrimaryKey(String id) {
        return gkVisitPhotoEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkVisitPhotoEntity record) {
        return gkVisitPhotoEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkVisitPhotoEntity record) {
        return gkVisitPhotoEntityMapper.updateByPrimaryKey(record);
    }

}
