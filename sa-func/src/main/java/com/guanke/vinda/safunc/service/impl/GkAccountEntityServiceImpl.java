package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkAccountEntityMapper;
import com.guanke.vinda.safunc.service.GkAccountEntityService;
import com.guanke.vinda.samodels.model.entity.GkAccountEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GkAccountEntityServiceImpl implements GkAccountEntityService{

    @Resource
    private GkAccountEntityMapper gkAccountEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkAccountEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkAccountEntity record) {
        return gkAccountEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkAccountEntity record) {
        return gkAccountEntityMapper.insertSelective(record);
    }

    @Override
    public GkAccountEntity selectByPrimaryKey(String id) {
        return gkAccountEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkAccountEntity record) {
        return gkAccountEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkAccountEntity record) {
        return gkAccountEntityMapper.updateByPrimaryKey(record);
    }

}
