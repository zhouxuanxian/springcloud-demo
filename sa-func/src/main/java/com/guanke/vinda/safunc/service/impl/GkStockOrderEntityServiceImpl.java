package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.GkStockOrderEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkStockOrderEntity;
import com.guanke.vinda.safunc.service.GkStockOrderEntityService;
@Service
public class GkStockOrderEntityServiceImpl implements GkStockOrderEntityService{

    @Resource
    private GkStockOrderEntityMapper gkStockOrderEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkStockOrderEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkStockOrderEntity record) {
        return gkStockOrderEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(GkStockOrderEntity record) {
        return gkStockOrderEntityMapper.insertSelective(record);
    }

    @Override
    public GkStockOrderEntity selectByPrimaryKey(String id) {
        return gkStockOrderEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkStockOrderEntity record) {
        return gkStockOrderEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkStockOrderEntity record) {
        return gkStockOrderEntityMapper.updateByPrimaryKey(record);
    }

}
