package com.guanke.vinda.safunc.service;

import com.guanke.vinda.samodels.model.entity.SaUserInformationEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Nicemorning
 */
public interface SaUserInfoService {

    int deleteByPrimaryKey(String id);

    int insert(SaUserInformationEntity record);

    int insertSelective(SaUserInformationEntity record);

    SaUserInformationEntity selectByPrimaryKey(String id);

    SaUserInformationEntity selectByPositionIdAndEmpId(String positionId, String empId);

    int updateByPrimaryKeySelective(SaUserInformationEntity record);

    int updateByPrimaryKey(SaUserInformationEntity record);

    Map<String, Object> selectCrmUserInfoByPhone(String phone);

    Boolean isOuterUserInCrmByPhone(String phone);

    /**
     * 通过用户的职位ID所在组织ID获取该用户可查看的所有省、市办
     *
     * @param isPosition 如果是通过职位，则为true，反之则为false以组织查找
     * @param id         组织ID
     * @return 返回符合条件的省、市办名称
     */
    List<Map<String, Object>> selectBusinessOfficeByPositionIdAndOrgIdAndWxType(Boolean isPosition, String id);

    /**
     * 通过用户的ROW_ID获取该用户的职位相关信息
     *
     * @param rowId 员工对应的CRM人员表的ROW_ID
     * @return 该员工的职位相关信息
     */
    Map<String, String> selectUserPositionInformation(String rowId);

    /**
     * 根据用户的员工ID获取该用户在个人设置界面中的基本信息
     *
     * @param empId 用户的员工ID
     * @return 返回该员工ID对应的设置页所需的信息
     */
    Map<String, Object> getUserSettingsInfoByEmpId(String empId);

    SaUserInformationEntity getUserInformationByPhone(String phone);
}
