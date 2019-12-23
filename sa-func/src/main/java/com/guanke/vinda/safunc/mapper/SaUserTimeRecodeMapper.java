package com.guanke.vinda.safunc.mapper;
import com.guanke.vinda.samodels.model.entity.SaUserTimeRecodeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaUserTimeRecodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaUserTimeRecodeEntity record);

    int insertSelective(SaUserTimeRecodeEntity record);

    SaUserTimeRecodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SaUserTimeRecodeEntity record);

    int updateByPrimaryKey(SaUserTimeRecodeEntity record);

    SaUserTimeRecodeEntity selectByRowId(@Param("rowId")String rowId);

    SaUserTimeRecodeEntity selectByPhone(@Param("phone")String phone);

}