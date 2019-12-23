package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.safunc.mapper.GkAccountSalesReportEntityMapper;
import com.guanke.vinda.samodels.model.entity.GkAccountSalesReportEntity;
import com.guanke.vinda.safunc.service.GkAccountSalesReportEntityService;

@Service
public class GkAccountSalesReportEntityServiceImpl implements GkAccountSalesReportEntityService {

    @Resource
    private GkAccountSalesReportEntityMapper gkAccountSalesReportEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkAccountSalesReportEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.insertSelective(record);
    }

    @Override
    public GkAccountSalesReportEntity selectByPrimaryKey(String id) {
        return gkAccountSalesReportEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkAccountSalesReportEntity record) {
        return gkAccountSalesReportEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<GkAccountSalesReportEntity> list) {
        return gkAccountSalesReportEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<GkAccountSalesReportEntity> list) {
        return gkAccountSalesReportEntityMapper.batchInsert(list);
    }

}

