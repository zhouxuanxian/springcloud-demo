package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.GkCostHistoryTemp;

/**
 * author:ldm
 * date:14:15 2019/11/18
 * describe: 默认描述
 */
public interface GkCostHistoryTempService {


    int deleteByPrimaryKey(String id);

    int insert(GkCostHistoryTemp record);

    int insertSelective(GkCostHistoryTemp record);

    GkCostHistoryTemp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCostHistoryTemp record);

    int updateByPrimaryKey(GkCostHistoryTemp record);

}







