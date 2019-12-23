package com.guanke.vinda.safunc.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.guanke.vinda.safunc.mapper.CxGtReimEntityMapper;
import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;
import com.guanke.vinda.safunc.service.CxGtReimEntityService;
import java.util.List;

@Service
public class CxGtReimEntityServiceImpl implements CxGtReimEntityService {

    @Resource
    private CxGtReimEntityMapper cxGtReimEntityMapper;

    @Override
    public int insert(CxGtReimEntity record) {
        return cxGtReimEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(CxGtReimEntity record) {
        return cxGtReimEntityMapper.insertSelective(record);
    }

    @Override
    public int batchInsert(List<CxGtReimEntity> list) {
        return cxGtReimEntityMapper.batchInsert(list);
    }
}




