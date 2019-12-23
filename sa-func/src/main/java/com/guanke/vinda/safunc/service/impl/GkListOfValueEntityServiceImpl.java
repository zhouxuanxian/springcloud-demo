package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.GkListOfValueEntity;
import com.guanke.vinda.safunc.mapper.GkListOfValueEntityMapper;
import com.guanke.vinda.safunc.service.GkListOfValueEntityService;
@Service
public class GkListOfValueEntityServiceImpl implements GkListOfValueEntityService{

    @Resource
    private GkListOfValueEntityMapper gkListOfValueEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkListOfValueEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkListOfValueEntity record) {
        return gkListOfValueEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkListOfValueEntity record) {
        return gkListOfValueEntityMapper.insertSelective(record);
    }

    @Override
    public GkListOfValueEntity selectByPrimaryKey(String id) {
        return gkListOfValueEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkListOfValueEntity record) {
        return gkListOfValueEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkListOfValueEntity record) {
        return gkListOfValueEntityMapper.updateByPrimaryKey(record);
    }

}
