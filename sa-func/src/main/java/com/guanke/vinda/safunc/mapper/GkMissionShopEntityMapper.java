package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkMissionShopEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GkMissionShopEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkMissionShopEntity record);

    int insertOrUpdate(GkMissionShopEntity record);

    int insertOrUpdateSelective(GkMissionShopEntity record);

    int insertSelective(GkMissionShopEntity record);

    GkMissionShopEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkMissionShopEntity record);

    int updateByPrimaryKey(GkMissionShopEntity record);

    int updateBatch(List<GkMissionShopEntity> list);

    int batchInsert(@Param("list") List<GkMissionShopEntity> list);

    List<Map<String, Object>> selectMissionPhotoListByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                                @Param("empId") String empId,
                                                                                @Param("date") String date);
}