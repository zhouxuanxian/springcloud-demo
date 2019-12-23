package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkCompetitorPhotoMapper;
import com.guanke.vinda.samodels.model.entity.GkCompetitorPhotoEntity;
import com.guanke.vinda.safunc.service.GkCompetitorPhotoService;
@Service
public class GkCompetitorPhotoServiceImpl implements GkCompetitorPhotoService{

    @Resource
    private GkCompetitorPhotoMapper gkCompetitorPhotoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCompetitorPhotoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCompetitorPhotoEntity record) {
        return gkCompetitorPhotoMapper.insert(record);
    }

    @Override
    public int insertSelective(GkCompetitorPhotoEntity record) {
        return gkCompetitorPhotoMapper.insertSelective(record);
    }

    @Override
    public GkCompetitorPhotoEntity selectByPrimaryKey(String id) {
        return gkCompetitorPhotoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCompetitorPhotoEntity record) {
        return gkCompetitorPhotoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCompetitorPhotoEntity record) {
        return gkCompetitorPhotoMapper.updateByPrimaryKey(record);
    }

}
