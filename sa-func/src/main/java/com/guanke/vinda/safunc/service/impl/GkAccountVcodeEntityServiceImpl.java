package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.GkAccountVcodeEntity;
import com.guanke.vinda.safunc.mapper.GkAccountVcodeEntityMapper;
import com.guanke.vinda.safunc.service.GkAccountVcodeEntityService;

@Service
public class GkAccountVcodeEntityServiceImpl implements GkAccountVcodeEntityService {

    @Resource
    private GkAccountVcodeEntityMapper gkAccountVcodeEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkAccountVcodeEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkAccountVcodeEntity record) {
        return gkAccountVcodeEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkAccountVcodeEntity record) {
        return gkAccountVcodeEntityMapper.insertSelective(record);
    }

    @Override
    public GkAccountVcodeEntity selectByPrimaryKey(String id) {
        return gkAccountVcodeEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkAccountVcodeEntity record) {
        return gkAccountVcodeEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkAccountVcodeEntity record) {
        return gkAccountVcodeEntityMapper.updateByPrimaryKey(record);
    }

}

