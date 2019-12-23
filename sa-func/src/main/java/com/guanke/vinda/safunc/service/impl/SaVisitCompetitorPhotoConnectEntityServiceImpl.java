package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitCompetitorPhotoConnectEntity;
import com.guanke.vinda.safunc.mapper.SaVisitCompetitorPhotoConnectEntityMapper;
import com.guanke.vinda.safunc.service.SaVisitCompetitorPhotoConnectEntityService;
@Service
public class SaVisitCompetitorPhotoConnectEntityServiceImpl implements SaVisitCompetitorPhotoConnectEntityService{

    @Resource
    private SaVisitCompetitorPhotoConnectEntityMapper saVisitCompetitorPhotoConnectEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saVisitCompetitorPhotoConnectEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.insertSelective(record);
    }

    @Override
    public SaVisitCompetitorPhotoConnectEntity selectByPrimaryKey(String id) {
        return saVisitCompetitorPhotoConnectEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaVisitCompetitorPhotoConnectEntity record) {
        return saVisitCompetitorPhotoConnectEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaVisitCompetitorPhotoConnectEntity> list) {
        return saVisitCompetitorPhotoConnectEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaVisitCompetitorPhotoConnectEntity> list) {
        return saVisitCompetitorPhotoConnectEntityMapper.batchInsert(list);
    }

}
