package com.guanke.vinda.sasync.service.impl;

import com.guanke.vinda.samodels.model.entity.GkWsLogEntity;
import com.guanke.vinda.sasync.mapper.GkWsLogMapper;
import com.guanke.vinda.sasync.service.GkWsLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GkWsLogServiceImpl implements GkWsLogService {

    @Resource
    private GkWsLogMapper gkWsLogMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkWsLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkWsLogEntity record) {
        return gkWsLogMapper.insert(record);
    }

    @Override
    public int insertSelective(GkWsLogEntity record) {
        return gkWsLogMapper.insertSelective(record);
    }

    @Override
    public GkWsLogEntity selectByPrimaryKey(String id) {
        return gkWsLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkWsLogEntity record) {
        return gkWsLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkWsLogEntity record) {
        return gkWsLogMapper.updateByPrimaryKey(record);
    }

}
