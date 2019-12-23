package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.GkExpenseEntity;

public interface GkExpenseEntityService {


    int deleteByPrimaryKey(String id);

    int insert(GkExpenseEntity record);

    int insertOrUpdate(GkExpenseEntity record);

    int insertOrUpdateSelective(GkExpenseEntity record);

    int insertSelective(GkExpenseEntity record);

    GkExpenseEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkExpenseEntity record);

    int updateByPrimaryKey(GkExpenseEntity record);

    int updateBatch(List<GkExpenseEntity> list);

    int batchInsert(List<GkExpenseEntity> list);

}

