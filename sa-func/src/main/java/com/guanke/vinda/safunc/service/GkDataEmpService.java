package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkDataEmpEntity;

public interface GkDataEmpService {


    int deleteByPrimaryKey(String id);

    int insert(GkDataEmpEntity record);

    int insertSelective(GkDataEmpEntity record);

    GkDataEmpEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDataEmpEntity record);

    int updateByPrimaryKey(GkDataEmpEntity record);

}


