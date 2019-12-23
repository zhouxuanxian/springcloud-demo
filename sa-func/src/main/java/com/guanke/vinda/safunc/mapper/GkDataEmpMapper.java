package com.guanke.vinda.safunc.mapper;
import com.guanke.vinda.samodels.model.entity.GkDataEmpEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GkDataEmpMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkDataEmpEntity record);

    int insertSelective(GkDataEmpEntity record);

    GkDataEmpEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkDataEmpEntity record);

    int updateByPrimaryKey(GkDataEmpEntity record);

    List<GkDataEmpEntity> selectByPositionIdAndRecordDate(@Param("positionId")String positionId,
                                                          @Param("recordDate")Date recordDate);

}