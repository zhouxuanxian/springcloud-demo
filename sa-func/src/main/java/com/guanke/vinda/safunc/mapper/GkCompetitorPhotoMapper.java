package com.guanke.vinda.safunc.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.guanke.vinda.samodels.model.entity.GkCompetitorPhotoEntity;

public interface GkCompetitorPhotoMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkCompetitorPhotoEntity record);

    int insertSelective(GkCompetitorPhotoEntity record);

    GkCompetitorPhotoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkCompetitorPhotoEntity record);

    int updateByPrimaryKey(GkCompetitorPhotoEntity record);

    List<GkCompetitorPhotoEntity> selectByCompetitorIdAndVisitId(@Param("competitorId")String competitorId,
                                                                 @Param("visitId")String visitId);


}