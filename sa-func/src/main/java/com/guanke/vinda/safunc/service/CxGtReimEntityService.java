package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;import java.util.List;

public interface CxGtReimEntityService {


    int insert(CxGtReimEntity record);

    int insertSelective(CxGtReimEntity record);

    int batchInsert(List<CxGtReimEntity> list);

}




