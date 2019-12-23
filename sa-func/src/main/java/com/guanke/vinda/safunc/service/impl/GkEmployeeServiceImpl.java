package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkEmployeeMapper;
import com.guanke.vinda.samodels.model.entity.GkEmployeeEntity;
import com.guanke.vinda.safunc.service.GkEmployeeService;
@Service
public class GkEmployeeServiceImpl implements GkEmployeeService{

    @Resource
    private GkEmployeeMapper gkEmployeeMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkEmployeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkEmployeeEntity record) {
        return gkEmployeeMapper.insert(record);
    }

    @Override
    public int insertSelective(GkEmployeeEntity record) {
        return gkEmployeeMapper.insertSelective(record);
    }

    @Override
    public GkEmployeeEntity selectByPrimaryKey(String id) {
        return gkEmployeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkEmployeeEntity record) {
        return gkEmployeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkEmployeeEntity record) {
        return gkEmployeeMapper.updateByPrimaryKey(record);
    }

}
