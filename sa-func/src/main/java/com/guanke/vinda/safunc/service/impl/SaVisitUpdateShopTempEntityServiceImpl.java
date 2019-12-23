package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.SaVisitUpdateShopTempEntityMapper;
import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitUpdateShopTempEntity;
import com.guanke.vinda.safunc.service.SaVisitUpdateShopTempEntityService;
@Service
public class SaVisitUpdateShopTempEntityServiceImpl implements SaVisitUpdateShopTempEntityService{

    @Resource
    private SaVisitUpdateShopTempEntityMapper saVisitUpdateShopTempEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saVisitUpdateShopTempEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.insertSelective(record);
    }

    @Override
    public SaVisitUpdateShopTempEntity selectByPrimaryKey(String id) {
        return saVisitUpdateShopTempEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaVisitUpdateShopTempEntity record) {
        return saVisitUpdateShopTempEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaVisitUpdateShopTempEntity> list) {
        return saVisitUpdateShopTempEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaVisitUpdateShopTempEntity> list) {
        return saVisitUpdateShopTempEntityMapper.batchInsert(list);
    }

}
