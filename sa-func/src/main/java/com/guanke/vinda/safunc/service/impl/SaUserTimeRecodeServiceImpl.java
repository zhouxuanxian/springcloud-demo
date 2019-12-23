package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.SaUserTimeRecodeMapper;
import com.guanke.vinda.safunc.service.SaUserTimeRecodeService;
import com.guanke.vinda.samodels.model.entity.SaUserTimeRecodeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SaUserTimeRecodeServiceImpl implements SaUserTimeRecodeService {

    @Resource
    private SaUserTimeRecodeMapper saUserTimeRecodeMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saUserTimeRecodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaUserTimeRecodeEntity record) {
        return saUserTimeRecodeMapper.insert(record);
    }

    @Override
    public int insertSelective(SaUserTimeRecodeEntity record) {
        return saUserTimeRecodeMapper.insertSelective(record);
    }

    @Override
    public SaUserTimeRecodeEntity selectByPrimaryKey(String id) {
        return saUserTimeRecodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaUserTimeRecodeEntity record) {
        return saUserTimeRecodeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaUserTimeRecodeEntity record) {
        return saUserTimeRecodeMapper.updateByPrimaryKey(record);
    }

    @Override
    public SaUserTimeRecodeEntity selectByRowId(String rowId) {
        return saUserTimeRecodeMapper.selectByRowId(rowId);
    }

    @Override
    public SaUserTimeRecodeEntity selectByPhone(String phone) {
        return saUserTimeRecodeMapper.selectByPhone(phone);
    }
}
