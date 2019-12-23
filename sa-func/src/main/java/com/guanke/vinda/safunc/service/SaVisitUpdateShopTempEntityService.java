package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.SaVisitUpdateShopTempEntity;
public interface SaVisitUpdateShopTempEntityService{


    int deleteByPrimaryKey(String id);

    int insert(SaVisitUpdateShopTempEntity record);

    int insertOrUpdate(SaVisitUpdateShopTempEntity record);

    int insertOrUpdateSelective(SaVisitUpdateShopTempEntity record);

    int insertSelective(SaVisitUpdateShopTempEntity record);

    SaVisitUpdateShopTempEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitUpdateShopTempEntity record);

    int updateByPrimaryKey(SaVisitUpdateShopTempEntity record);

    int updateBatch(List<SaVisitUpdateShopTempEntity> list);

    int batchInsert(List<SaVisitUpdateShopTempEntity> list);

}
