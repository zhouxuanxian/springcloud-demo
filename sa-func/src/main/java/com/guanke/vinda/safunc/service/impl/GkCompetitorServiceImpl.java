package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkCompetitorMapper;
import com.guanke.vinda.samodels.model.entity.GkCompetitorEntity;
import com.guanke.vinda.safunc.service.GkCompetitorService;
@Service
public class GkCompetitorServiceImpl implements GkCompetitorService{

    @Resource
    private GkCompetitorMapper gkCompetitorMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCompetitorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCompetitorEntity record) {
        return gkCompetitorMapper.insert(record);
    }

    @Override
    public int insertSelective(GkCompetitorEntity record) {
        return gkCompetitorMapper.insertSelective(record);
    }

    @Override
    public GkCompetitorEntity selectByPrimaryKey(String id) {
        return gkCompetitorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCompetitorEntity record) {
        return gkCompetitorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCompetitorEntity record) {
        return gkCompetitorMapper.updateByPrimaryKey(record);
    }

}
