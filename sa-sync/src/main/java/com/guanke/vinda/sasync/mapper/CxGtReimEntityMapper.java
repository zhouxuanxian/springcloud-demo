package com.guanke.vinda.sasync.mapper;

import com.guanke.vinda.samodels.model.entity.CxGtReimEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * author:ldm
 * date:15:06 2019/11/18
 * describe: 默认描述
 */
public interface CxGtReimEntityMapper {
    int insert(CxGtReimEntity record);

    int insertSelective(CxGtReimEntity record);

    int batchInsert(@Param("list") List<CxGtReimEntity> list);

    int updateByRowId(@Param("updated") CxGtReimEntity updated, @Param("rowId") String rowId);

    CxGtReimEntity selectByRowId(@Param("rowId")String rowId);


    List<Map<String, Object>> selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(
            @Param("positionId") String positionId, @Param("empId") String empId, @Param("WxType") String WxType, @Param("Keyword") String Keyword);

    List<Map<String, Object>> selectMoreCostListByPositionAndEmpIdSortByUpdTime(
            @Param("positionId") String positionId, @Param("empId") String empId, @Param("WxType") String WxType, @Param("Keyword") String Keyword,
            @Param("Approval_Status") String Approval_Status, @Param("Reim_Status") String Reim_Status);

    Map<String,Object> getWxType(@Param("positionId") String positionId, @Param("intId") String intId);

    Map<String,Object> selectEmployeeById(@Param("id") String id);

    List<Map<String, Object>> checkSuperiorManByPosId(@Param("wxType") String wxType,@Param("row_Id") String row_Id);
    List<Map<String, Object>> checkCityAndProvinceOffice(@Param("positionId")String positionId,@Param("wxType") String wxType);
    List<Map<String, Object>> checkPromoteByPosId(@Param("row_Id")String row_Id);

    String getGtCode(@Param("code") String code);

    List<Map<String,Object>> selctSuperiorPosition(@Param("positionId") String positionId,@Param("wxType") String wxType );
}