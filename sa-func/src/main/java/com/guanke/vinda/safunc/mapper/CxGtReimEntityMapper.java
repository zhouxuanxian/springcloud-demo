package com.guanke.vinda.safunc.mapper;

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

    int updateByRowId(@Param("updated")CxGtReimEntity updated,@Param("rowId")String rowId);

    CxGtReimEntity selectAllByRowId(@Param("rowId")String rowId);
    Map<String,Object> selectAllByRowIdReturnMap(@Param("rowId")String rowId);

    List<Map<String, Object>> selectNoDoneCostListByPositionAndEmpIdSortByUpdTime(@Param("dateTime") String dateTime,@Param("approvalStatus") String approvalStatus,
            @Param("positionId") String positionId, @Param("empId") String empId, @Param("wxType") String wxType, @Param("keyword") String keyword);

    List<Map<String, Object>> selectMoreCostListByPositionAndEmpIdSortByUpdTime(
            @Param("positionId") String positionId, @Param("empId") String empId, @Param("wxType") String wxType, @Param("keyword") String keyword,
            @Param("approvalStatus") String approvalStatus, @Param("reimStatus") String reimStatus);

    Map<String,Object> getWxType(@Param("positionId") String positionId, @Param("intId") String intId);
    Map<String,Object> getCostCancelGTById(@Param("rowId") String rowId);

    List<Map<String,Object>> selectAuditListByRowId(String rowId);


}