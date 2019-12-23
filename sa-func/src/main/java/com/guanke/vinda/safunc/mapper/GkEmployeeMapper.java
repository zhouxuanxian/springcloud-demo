package com.guanke.vinda.safunc.mapper;
import com.guanke.vinda.samodels.model.entity.GkEmployeeEntity;

public interface GkEmployeeMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkEmployeeEntity record);

    int insertSelective(GkEmployeeEntity record);

    GkEmployeeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkEmployeeEntity record);

    int updateByPrimaryKey(GkEmployeeEntity record);

}