package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkDailyMapper;
import com.guanke.vinda.safunc.service.GkDailyService;
import com.guanke.vinda.samodels.model.entity.GkDailyEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GkDailyServiceImpl implements GkDailyService {

    @Resource
    private GkDailyMapper gkDailyMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkDailyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkDailyEntity record) {
        return gkDailyMapper.insert(record);
    }

    @Override
    public int insertSelective(GkDailyEntity record) {
        return gkDailyMapper.insertSelective(record);
    }

    @Override
    public GkDailyEntity selectByPrimaryKey(String id) {
        return gkDailyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkDailyEntity record) {
        return gkDailyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkDailyEntity record) {
        return gkDailyMapper.updateByPrimaryKey(record);
    }

}
