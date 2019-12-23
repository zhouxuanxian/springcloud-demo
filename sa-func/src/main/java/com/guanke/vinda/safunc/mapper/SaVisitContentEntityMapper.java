package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaVisitContentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaVisitContentEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaVisitContentEntity record);

    int insertOrUpdate(SaVisitContentEntity record);

    int insertOrUpdateSelective(SaVisitContentEntity record);

    int insertSelective(SaVisitContentEntity record);

    SaVisitContentEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitContentEntity record);

    int updateByPrimaryKey(SaVisitContentEntity record);

    int updateBatch(List<SaVisitContentEntity> list);

    int batchInsert(@Param("list") List<SaVisitContentEntity> list);

    SaVisitContentEntity selectFirstByGkVisitId(@Param("gkVisitId")String gkVisitId);
}