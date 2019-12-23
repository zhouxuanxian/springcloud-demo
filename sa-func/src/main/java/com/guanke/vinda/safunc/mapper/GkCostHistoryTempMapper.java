package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkCostHistoryTemp;

/**
 * author:ldm
 * date:14:50 2019/11/21
 * describe: 默认描述
 */
public interface GkCostHistoryTempMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCostHistoryTemp record);

    int insertSelective(GkCostHistoryTemp record);

    GkCostHistoryTemp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCostHistoryTemp record);

    int updateByPrimaryKey(GkCostHistoryTemp record);
}