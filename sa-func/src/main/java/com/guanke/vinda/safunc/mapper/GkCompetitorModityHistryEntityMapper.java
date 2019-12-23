package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkCompetitorModityHistryEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GkCompetitorModityHistryEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorModityHistryEntity record);

    int insertOrUpdate(GkCompetitorModityHistryEntity record);

    int insertOrUpdateSelective(GkCompetitorModityHistryEntity record);

    int insertSelective(GkCompetitorModityHistryEntity record);

    GkCompetitorModityHistryEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorModityHistryEntity record);

    int updateByPrimaryKey(GkCompetitorModityHistryEntity record);

    int updateBatch(List<GkCompetitorModityHistryEntity> list);

    int batchInsert(@Param("list") List<GkCompetitorModityHistryEntity> list);

    GkCompetitorModityHistryEntity selectFirstByCompetitorId(@Param("competitorId")String competitorId);


}