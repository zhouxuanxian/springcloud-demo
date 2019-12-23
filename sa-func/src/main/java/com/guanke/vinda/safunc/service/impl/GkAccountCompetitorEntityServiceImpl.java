package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkAccountCompetitorEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkAccountCompetitorEntity;
import com.guanke.vinda.safunc.service.GkAccountCompetitorEntityService;
@Service
public class GkAccountCompetitorEntityServiceImpl implements GkAccountCompetitorEntityService{

    @Resource
    private GkAccountCompetitorEntityMapper gkAccountCompetitorEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkAccountCompetitorEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkAccountCompetitorEntity record) {
        return gkAccountCompetitorEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkAccountCompetitorEntity record) {
        return gkAccountCompetitorEntityMapper.insertSelective(record);
    }

    @Override
    public GkAccountCompetitorEntity selectByPrimaryKey(String id) {
        return gkAccountCompetitorEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkAccountCompetitorEntity record) {
        return gkAccountCompetitorEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkAccountCompetitorEntity record) {
        return gkAccountCompetitorEntityMapper.updateByPrimaryKey(record);
    }

}
