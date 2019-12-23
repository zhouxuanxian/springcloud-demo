package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.GkCostHistoryTempMapper;
import com.guanke.vinda.samodels.model.entity.GkCostHistoryTemp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.guanke.vinda.safunc.service.GkCostHistoryTempService;

/**
 * author:ldm
 * date:14:15 2019/11/18
 * describe: 默认描述
 */
@Service
public class GkCostHistoryTempServiceImpl implements GkCostHistoryTempService {

    @Resource
    private GkCostHistoryTempMapper gkCostHistoryTempMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCostHistoryTempMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCostHistoryTemp record) {
        return gkCostHistoryTempMapper.insert(record);
    }

    @Override
    public int insertSelective(GkCostHistoryTemp record) {
        return gkCostHistoryTempMapper.insertSelective(record);
    }

    @Override
    public GkCostHistoryTemp selectByPrimaryKey(String id) {
        return gkCostHistoryTempMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCostHistoryTemp record) {
        return gkCostHistoryTempMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCostHistoryTemp record) {
        return gkCostHistoryTempMapper.updateByPrimaryKey(record);
    }

}







