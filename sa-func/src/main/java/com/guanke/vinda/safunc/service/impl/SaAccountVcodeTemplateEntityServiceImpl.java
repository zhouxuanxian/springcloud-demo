package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.guanke.vinda.safunc.mapper.SaAccountVcodeTemplateEntityMapper;
import com.guanke.vinda.samodels.model.entity.SaAccountVcodeTemplateEntity;
import com.guanke.vinda.safunc.service.SaAccountVcodeTemplateEntityService;
@Service
public class SaAccountVcodeTemplateEntityServiceImpl implements SaAccountVcodeTemplateEntityService{

    @Resource
    private SaAccountVcodeTemplateEntityMapper saAccountVcodeTemplateEntityMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saAccountVcodeTemplateEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.insertSelective(record);
    }

    @Override
    public SaAccountVcodeTemplateEntity selectByPrimaryKey(String id) {
        return saAccountVcodeTemplateEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaAccountVcodeTemplateEntity record) {
        return saAccountVcodeTemplateEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<SaAccountVcodeTemplateEntity> list) {
        return saAccountVcodeTemplateEntityMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<SaAccountVcodeTemplateEntity> list) {
        return saAccountVcodeTemplateEntityMapper.batchInsert(list);
    }

}
