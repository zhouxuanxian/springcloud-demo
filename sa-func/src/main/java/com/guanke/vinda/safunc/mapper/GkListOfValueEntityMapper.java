package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkListOfValueEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkListOfValueEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkListOfValueEntity record);

    int insertSelective(GkListOfValueEntity record);

    GkListOfValueEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkListOfValueEntity record);

    int updateByPrimaryKey(GkListOfValueEntity record);

    List<Map<String, Object>> selectLovInfoByInitCodeAndKeyword(@Param("keyword") String keyword,
                                                                @Param("initCodeList") List<String> initCodeList);

    List<Map<String, Object>> selectLovListByType(@Param("type") String type);
}