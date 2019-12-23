package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.GkDailyPhotoEntity;
import com.guanke.vinda.safunc.mapper.GkDailyPhotoMapper;
import com.guanke.vinda.safunc.service.GkDailyPhotoService;
@Service
public class GkDailyPhotoServiceImpl implements GkDailyPhotoService{

    @Resource
    private GkDailyPhotoMapper gkDailyPhotoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkDailyPhotoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkDailyPhotoEntity record) {
        return gkDailyPhotoMapper.insert(record);
    }

    @Override
    public int insertSelective(GkDailyPhotoEntity record) {
        return gkDailyPhotoMapper.insertSelective(record);
    }

    @Override
    public GkDailyPhotoEntity selectByPrimaryKey(String id) {
        return gkDailyPhotoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkDailyPhotoEntity record) {
        return gkDailyPhotoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkDailyPhotoEntity record) {
        return gkDailyPhotoMapper.updateByPrimaryKey(record);
    }

}
