package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.GkGtReimTempEntity;
import com.guanke.vinda.safunc.mapper.GkGtReimTempEntityMapper;
import com.guanke.vinda.safunc.service.GkGtReimTempEntityService;
@Service
public class GkGtReimTempEntityServiceImpl implements GkGtReimTempEntityService{

    @Resource
    private GkGtReimTempEntityMapper gkGtReimTempEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkGtReimTempEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkGtReimTempEntity record) {
        return gkGtReimTempEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkGtReimTempEntity record) {
        return gkGtReimTempEntityMapper.insertSelective(record);
    }

    @Override
    public GkGtReimTempEntity selectByPrimaryKey(String id) {
        return gkGtReimTempEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkGtReimTempEntity record) {
        return gkGtReimTempEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkGtReimTempEntity record) {
        return gkGtReimTempEntityMapper.updateByPrimaryKey(record);
    }

}
