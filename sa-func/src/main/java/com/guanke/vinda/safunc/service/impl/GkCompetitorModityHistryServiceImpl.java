package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkCompetitorModityHistryMapper;
import com.guanke.vinda.samodels.model.entity.GkCompetitorModityHistryEntity;
import com.guanke.vinda.safunc.service.GkCompetitorModityHistryService;
@Service
public class GkCompetitorModityHistryServiceImpl implements GkCompetitorModityHistryService{

    @Resource
    private GkCompetitorModityHistryMapper gkCompetitorModityHistryMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCompetitorModityHistryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryMapper.insert(record);
    }

    @Override
    public int insertSelective(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryMapper.insertSelective(record);
    }

    @Override
    public GkCompetitorModityHistryEntity selectByPrimaryKey(String id) {
        return gkCompetitorModityHistryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryMapper.updateByPrimaryKey(record);
    }

}
