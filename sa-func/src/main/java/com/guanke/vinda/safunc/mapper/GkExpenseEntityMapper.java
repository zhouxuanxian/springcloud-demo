package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkExpenseEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GkExpenseEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkExpenseEntity record);

    int insertOrUpdate(GkExpenseEntity record);

    int insertOrUpdateSelective(GkExpenseEntity record);

    int insertSelective(GkExpenseEntity record);

    GkExpenseEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkExpenseEntity record);

    int updateByPrimaryKey(GkExpenseEntity record);

    int updateBatch(List<GkExpenseEntity> list);

    int batchInsert(@Param("list") List<GkExpenseEntity> list);

    List<Map<String, Object>> selectExpensePhotosByPositionIdAndEmpIdAndDate(@Param("positionId") String positionId,
                                                                             @Param("empId") String empId,
                                                                             @Param("date") String date);
}