package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaVisitUpdateShopTempEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaVisitUpdateShopTempEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaVisitUpdateShopTempEntity record);

    int insertOrUpdate(SaVisitUpdateShopTempEntity record);

    int insertOrUpdateSelective(SaVisitUpdateShopTempEntity record);

    int insertSelective(SaVisitUpdateShopTempEntity record);

    SaVisitUpdateShopTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitUpdateShopTempEntity record);

    int updateByPrimaryKey(SaVisitUpdateShopTempEntity record);

    int updateBatch(List<SaVisitUpdateShopTempEntity> list);

    int batchInsert(@Param("list") List<SaVisitUpdateShopTempEntity> list);

    SaVisitUpdateShopTempEntity selectFirstByVisitId(@Param("visitId")String visitId);


}