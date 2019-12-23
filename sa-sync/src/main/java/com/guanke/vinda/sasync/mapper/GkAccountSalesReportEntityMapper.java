package com.guanke.vinda.sasync.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountSalesReportEntity;
import org.apache.ibatis.annotations.Param;

public interface GkAccountSalesReportEntityMapper {

    int insertSelective(GkAccountSalesReportEntity record);

    GkAccountSalesReportEntity selectByIdAndTime(@Param("shopId") String shopId, @Param("date") String date);

}