package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaVisitExtEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaVisitExtEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaVisitExtEntity record);

    int insertOrUpdate(SaVisitExtEntity record);

    int insertOrUpdateSelective(SaVisitExtEntity record);

    int insertSelective(SaVisitExtEntity record);

    SaVisitExtEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaVisitExtEntity record);

    int updateByPrimaryKey(SaVisitExtEntity record);

    int updateBatch(List<SaVisitExtEntity> list);

    int batchInsert(@Param("list") List<SaVisitExtEntity> list);

    SaVisitExtEntity selectFirstByParentId(@Param("parentId") String parentId);
}