package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.SaUserTimeRecodeEntity;

public interface SaUserTimeRecodeService {


    int deleteByPrimaryKey(String id);

    int insert(SaUserTimeRecodeEntity record);

    int insertSelective(SaUserTimeRecodeEntity record);

    SaUserTimeRecodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaUserTimeRecodeEntity record);

    int updateByPrimaryKey(SaUserTimeRecodeEntity record);

    SaUserTimeRecodeEntity selectByRowId(String rowId);

    SaUserTimeRecodeEntity selectByPhone(String phone);
}
