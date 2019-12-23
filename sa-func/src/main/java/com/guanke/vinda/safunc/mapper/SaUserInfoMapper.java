package com.guanke.vinda.safunc.mapper;

import com.guanke.vinda.samodels.model.entity.SaUserInformationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Nicemorning
 */
public interface SaUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SaUserInformationEntity record);

    int insertSelective(SaUserInformationEntity record);

    SaUserInformationEntity selectByPrimaryKey(String id);

    SaUserInformationEntity selectByPositionIdAndEmpId(@Param("positionId") String positionId,
                                                       @Param("empId") String empId);

    int updateByPrimaryKeySelective(SaUserInformationEntity record);

    int updateByPrimaryKey(SaUserInformationEntity record);

    Map<String, Object> selectCrmUserInfoByPhone(String phone);

    Boolean isOuterUserInCrmByPhone(@Param("phone") String phone);

    /**
     * 通过用户的职位ID所在组织ID获取该用户可查看的所有省、市办
     *
     * @param isPosition 如果是通过职位，则为true，反之则为false以组织查找
     * @param id         组织ID
     * @return 返回符合条件的省、市办名称
     */
    List<Map<String, Object>> selectBusinessOfficeByPositionIdAndOrgIdAndWxType(
            @Param("isPosition") Boolean isPosition,
            @Param("id") String id
    );

    /**
     * 通过用户的ROW_ID获取该用户的职位相关信息
     *
     * @param rowId 员工对应的CRM人员表的ROW_ID
     * @return 该员工的职位相关信息
     */
    Map<String, String> selectUserPositionInformationByRowId(@Param("rowId") String rowId);

    Map<String, Object> selectUserSettingsInfoByEmpId(@Param("empId") String empId);

    SaUserInformationEntity selectByPhone(@Param("phone")String phone);

}