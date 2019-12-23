package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkCostGtPhotoEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkCostGtPhotoEntity;
import com.guanke.vinda.safunc.service.GkCostGtPhotoEntityService;
@Service
public class GkCostGtPhotoEntityServiceImpl implements GkCostGtPhotoEntityService{

    @Resource
    private GkCostGtPhotoEntityMapper gkCostGtPhotoEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCostGtPhotoEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCostGtPhotoEntity record) {
        return gkCostGtPhotoEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkCostGtPhotoEntity record) {
        return gkCostGtPhotoEntityMapper.insertSelective(record);
    }

    @Override
    public GkCostGtPhotoEntity selectByPrimaryKey(String id) {
        return gkCostGtPhotoEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCostGtPhotoEntity record) {
        return gkCostGtPhotoEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCostGtPhotoEntity record) {
        return gkCostGtPhotoEntityMapper.updateByPrimaryKey(record);
    }

}
