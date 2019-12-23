package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.safunc.mapper.GkExpenseEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkExpenseEntity;
import com.guanke.vinda.safunc.service.GkExpenseEntityService;

@Service
public class GkExpenseEntityServiceImpl implements GkExpenseEntityService {

    @Resource
    private GkExpenseEntityMapper gkExpenseEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkExpenseEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkExpenseEntity record) {
        return gkExpenseEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(GkExpenseEntity record) {
        return gkExpenseEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(GkExpenseEntity record) {
        return gkExpenseEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(GkExpenseEntity record) {
        return gkExpenseEntityMapper.insertSelective(record);
    }

    @Override
    public GkExpenseEntity selectByPrimaryKey(String id) {
        return gkExpenseEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkExpenseEntity record) {
        return gkExpenseEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkExpenseEntity record) {
        return gkExpenseEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<GkExpenseEntity> list) {
        return gkExpenseEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<GkExpenseEntity> list) {
        return gkExpenseEntityMapper.batchInsert(list);
    }

}

