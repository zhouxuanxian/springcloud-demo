package com.guanke.vinda.safunc.service;

import java.util.List;
import com.guanke.vinda.samodels.model.entity.GkMissionShopEntity;
public interface GkMissionShopEntityService{


    int deleteByPrimaryKey(String id);

    int insert(GkMissionShopEntity record);

    int insertOrUpdate(GkMissionShopEntity record);

    int insertOrUpdateSelective(GkMissionShopEntity record);

    int insertSelective(GkMissionShopEntity record);

    GkMissionShopEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkMissionShopEntity record);

    int updateByPrimaryKey(GkMissionShopEntity record);

    int updateBatch(List<GkMissionShopEntity> list);

    int batchInsert(List<GkMissionShopEntity> list);

}
