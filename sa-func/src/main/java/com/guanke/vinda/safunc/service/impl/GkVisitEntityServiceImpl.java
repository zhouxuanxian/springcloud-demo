package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkVisitEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkVisitEntity;
import com.guanke.vinda.safunc.service.GkVisitEntityService;
@Service
public class GkVisitEntityServiceImpl implements GkVisitEntityService{

    @Resource
    private GkVisitEntityMapper gkVisitEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkVisitEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkVisitEntity record) {
        return gkVisitEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkVisitEntity record) {
        return gkVisitEntityMapper.insertSelective(record);
    }

    @Override
    public GkVisitEntity selectByPrimaryKey(String id) {
        return gkVisitEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkVisitEntity record) {
        return gkVisitEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkVisitEntity record) {
        return gkVisitEntityMapper.updateByPrimaryKey(record);
    }

}
