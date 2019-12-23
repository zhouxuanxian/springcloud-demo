package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkDailyLikeMapper;
import com.guanke.vinda.safunc.service.GkDailyLikeService;
import com.guanke.vinda.samodels.model.entity.GkDailyLikeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GkDailyLikeServiceImpl implements GkDailyLikeService {

    @Resource
    private GkDailyLikeMapper gkDailyLikeMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkDailyLikeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkDailyLikeEntity record) {
        return gkDailyLikeMapper.insert(record);
    }

    @Override
    public int insertSelective(GkDailyLikeEntity record) {
        return gkDailyLikeMapper.insertSelective(record);
    }

    @Override
    public GkDailyLikeEntity selectByPrimaryKey(String id) {
        return gkDailyLikeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkDailyLikeEntity record) {
        return gkDailyLikeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkDailyLikeEntity record) {
        return gkDailyLikeMapper.updateByPrimaryKey(record);
    }

}
