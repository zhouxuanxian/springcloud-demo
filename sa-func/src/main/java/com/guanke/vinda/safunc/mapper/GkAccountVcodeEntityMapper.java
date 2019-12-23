package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.GkAccountVcodeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GkAccountVcodeEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(GkAccountVcodeEntity record);

    int insertSelective(GkAccountVcodeEntity record);

    GkAccountVcodeEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GkAccountVcodeEntity record);

    int updateByPrimaryKey(GkAccountVcodeEntity record);

    Map<String, Object> selectShopVCodeCnt(@Param("shopId") String shopId);

    List<Map<String, Object>> selectStockOrdersIdByAccountId(@Param("accountId") String accountId,
                                                             @Param("visitId") String visitId);

    List<Map<String, Object>> selectStockOrdersIdByVisitId(@Param("visitId") String visitId);

    List<GkAccountVcodeEntity> selectByAccountIdAndVCode(@Param("accountId") String accountId, @Param("vCode") String vCode);

    List<Map<String, Object>> selectVCodeInfoByVisitId(@Param("visitId") String visitId);

    List<Map<String, Object>> selectGkAccountVcodeByCondition(@Param("accountId") String accountId);
}