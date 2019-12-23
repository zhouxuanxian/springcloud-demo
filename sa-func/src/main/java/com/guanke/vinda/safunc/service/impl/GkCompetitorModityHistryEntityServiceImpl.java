package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.samodels.model.entity.GkCompetitorModityHistryEntity;
import java.util.List;
import com.guanke.vinda.safunc.mapper.GkCompetitorModityHistryEntityMapper;
import com.guanke.vinda.safunc.service.GkCompetitorModityHistryEntityService;

@Service
public class GkCompetitorModityHistryEntityServiceImpl implements GkCompetitorModityHistryEntityService {

    @Resource
    private GkCompetitorModityHistryEntityMapper gkCompetitorModityHistryEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return gkCompetitorModityHistryEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.insertSelective(record);
    }

    @Override
    public GkCompetitorModityHistryEntity selectByPrimaryKey(String id) {
        return gkCompetitorModityHistryEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GkCompetitorModityHistryEntity record) {
        return gkCompetitorModityHistryEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<GkCompetitorModityHistryEntity> list) {
        return gkCompetitorModityHistryEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<GkCompetitorModityHistryEntity> list) {
        return gkCompetitorModityHistryEntityMapper.batchInsert(list);
    }

}


