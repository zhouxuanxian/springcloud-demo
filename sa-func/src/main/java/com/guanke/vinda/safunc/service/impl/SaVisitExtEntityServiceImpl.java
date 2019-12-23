package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitExtEntity;
import com.guanke.vinda.safunc.mapper.SaVisitExtEntityMapper;
import com.guanke.vinda.safunc.service.SaVisitExtEntityService;

@Service
public class SaVisitExtEntityServiceImpl implements SaVisitExtEntityService {

    @Resource
    private SaVisitExtEntityMapper saVisitExtEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saVisitExtEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.insertSelective(record);
    }

    @Override
    public SaVisitExtEntity selectByPrimaryKey(String id) {
        return saVisitExtEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaVisitExtEntity record) {
        return saVisitExtEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaVisitExtEntity> list) {
        return saVisitExtEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaVisitExtEntity> list) {
        return saVisitExtEntityMapper.batchInsert(list);
    }

}


