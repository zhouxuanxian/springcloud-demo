package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkAccountChangeMapper;
import com.guanke.vinda.safunc.service.GkAccountChangeService;
import com.guanke.vinda.samodels.model.entity.GkAccountChangeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GkAccountChangeServiceImpl implements GkAccountChangeService {

    @Resource
    private GkAccountChangeMapper gkAccountChangeMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkAccountChangeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkAccountChangeEntity record) {
        return gkAccountChangeMapper.insert(record);
    }

    @Override
    public int insertSelective(GkAccountChangeEntity record) {
        return gkAccountChangeMapper.insertSelective(record);
    }

    @Override
    public GkAccountChangeEntity selectByPrimaryKey(String id) {
        return gkAccountChangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkAccountChangeEntity record) {
        return gkAccountChangeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkAccountChangeEntity record) {
        return gkAccountChangeMapper.updateByPrimaryKey(record);
    }

}



