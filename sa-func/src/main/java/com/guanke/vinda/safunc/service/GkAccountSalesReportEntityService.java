package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.GkAccountSalesReportEntity;

public interface GkAccountSalesReportEntityService {


    int deleteByPrimaryKey(String id);

    int insert(GkAccountSalesReportEntity record);

    int insertOrUpdate(GkAccountSalesReportEntity record);

    int insertOrUpdateSelective(GkAccountSalesReportEntity record);

    int insertSelective(GkAccountSalesReportEntity record);

    GkAccountSalesReportEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountSalesReportEntity record);

    int updateByPrimaryKey(GkAccountSalesReportEntity record);

    int updateBatch(List<GkAccountSalesReportEntity> list);

    int batchInsert(List<GkAccountSalesReportEntity> list);

}

