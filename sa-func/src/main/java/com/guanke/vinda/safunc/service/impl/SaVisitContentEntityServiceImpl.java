package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.SaVisitContentEntityMapper;
import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitContentEntity;
import com.guanke.vinda.safunc.service.SaVisitContentEntityService;
@Service
public class SaVisitContentEntityServiceImpl implements SaVisitContentEntityService{

    @Resource
    private SaVisitContentEntityMapper saVisitContentEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saVisitContentEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.insertSelective(record);
    }

    @Override
    public SaVisitContentEntity selectByPrimaryKey(String id) {
        return saVisitContentEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaVisitContentEntity record) {
        return saVisitContentEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaVisitContentEntity> list) {
        return saVisitContentEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaVisitContentEntity> list) {
        return saVisitContentEntityMapper.batchInsert(list);
    }

}
