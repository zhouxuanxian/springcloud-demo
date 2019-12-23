package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaCompetitorTemplateEntity;
import com.guanke.vinda.safunc.mapper.SaCompetitorTemplateEntityMapper;
import com.guanke.vinda.safunc.service.SaCompetitorTemplateEntityService;

@Service
public class SaCompetitorTemplateEntityServiceImpl implements SaCompetitorTemplateEntityService {

    @Resource
    private SaCompetitorTemplateEntityMapper saCompetitorTemplateEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saCompetitorTemplateEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.insertSelective(record);
    }

    @Override
    public SaCompetitorTemplateEntity selectByPrimaryKey(String id) {
        return saCompetitorTemplateEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaCompetitorTemplateEntity record) {
        return saCompetitorTemplateEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaCompetitorTemplateEntity> list) {
        return saCompetitorTemplateEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaCompetitorTemplateEntity> list) {
        return saCompetitorTemplateEntityMapper.batchInsert(list);
    }

}






