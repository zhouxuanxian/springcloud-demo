package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountChangeEntity;
import org.apache.ibatis.annotations.Param;

public interface GkAccountChangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkAccountChangeEntity record);

    int insertSelective(GkAccountChangeEntity record);

    GkAccountChangeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountChangeEntity record);

    int updateByPrimaryKey(GkAccountChangeEntity record);

    GkAccountChangeEntity selectByAccntId(@Param("accntId") String accntId);

    int selectCountByEmpIdAndDate(@Param("empId") String empId,
                                  @Param("date") String date);

}