package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkDataEmpMapper;
import com.guanke.vinda.safunc.service.GkDataEmpService;
import com.guanke.vinda.samodels.model.entity.GkDataEmpEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GkDataEmpServiceImpl implements GkDataEmpService {

    @Resource
    private GkDataEmpMapper gkDataEmpMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkDataEmpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkDataEmpEntity record) {
        return gkDataEmpMapper.insert(record);
    }

    @Override
    public int insertSelective(GkDataEmpEntity record) {
        return gkDataEmpMapper.insertSelective(record);
    }

    @Override
    public GkDataEmpEntity selectByPrimaryKey(String id) {
        return gkDataEmpMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkDataEmpEntity record) {
        return gkDataEmpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkDataEmpEntity record) {
        return gkDataEmpMapper.updateByPrimaryKey(record);
    }

}


