package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkAccountVcodeEntity;

public interface GkAccountVcodeEntityService {


    int deleteByPrimaryKey(String id);

    int insert(GkAccountVcodeEntity record);

    int insertSelective(GkAccountVcodeEntity record);

    GkAccountVcodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountVcodeEntity record);

    int updateByPrimaryKey(GkAccountVcodeEntity record);

}

