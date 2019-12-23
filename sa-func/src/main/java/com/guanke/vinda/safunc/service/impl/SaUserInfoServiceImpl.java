package com.guanke.vinda.safunc.service.impl;

import com.guanke.vinda.safunc.mapper.SaUserInfoMapper;
import com.guanke.vinda.safunc.service.SaUserInfoService;
import com.guanke.vinda.samodels.model.entity.SaUserInformationEntity;
import com.guanke.vinda.samodels.model.utils.UserPositionWxTypeHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Nicemorning
 */
@Service
public class SaUserInfoServiceImpl implements SaUserInfoService {

    @Resource
    private SaUserInfoMapper saUserInfoMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return saUserInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaUserInformationEntity record) {
        return saUserInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SaUserInformationEntity record) {
        return saUserInfoMapper.insertSelective(record);
    }

    @Override
    public SaUserInformationEntity selectByPrimaryKey(String id) {
        return saUserInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public SaUserInformationEntity selectByPositionIdAndEmpId(String positionId, String empId) {
        return saUserInfoMapper.selectByPositionIdAndEmpId(positionId, empId);
    }

    @Override
    public int updateByPrimaryKeySelective(SaUserInformationEntity record) {
        return saUserInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaUserInformationEntity record) {
        return saUserInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Map<String, Object> selectCrmUserInfoByPhone(String phone) {
        return saUserInfoMapper.selectCrmUserInfoByPhone(phone);
    }

    @Override
    public Boolean isOuterUserInCrmByPhone(String phone) {
        return saUserInfoMapper.isOuterUserInCrmByPhone(phone);
    }

    /**
     * 通过用户的职位ID所在组织ID获取该用户可查看的所有省、市办
     *
     * @param isPosition 如果是通过职位，则为true，反之则为false以组织查找
     * @param id         组织ID
     * @return 返回符合条件的省、市办名称
     */
    @Override
    public List<Map<String, Object>> selectBusinessOfficeByPositionIdAndOrgIdAndWxType(Boolean isPosition, String id) {
        return saUserInfoMapper.selectBusinessOfficeByPositionIdAndOrgIdAndWxType(isPosition, id);
    }

    /**
     * 通过用户的ROW_ID获取该用户的职位相关信息
     *
     * @param rowId 员工对应的CRM人员表的ROW_ID
     * @return 该员工的职位相关信息
     */
    @Override
    public Map<String, String> selectUserPositionInformation(String rowId) {
        Map<String, String> result = saUserInfoMapper.selectUserPositionInformationByRowId(rowId);
        String wxType = UserPositionWxTypeHelper.transformDB2WxType(result.get("wxType"));
        result.replace("wxType", wxType);
        return result;
    }

    /**
     * 根据用户的员工ID获取该用户在个人设置界面中的基本信息
     *
     * @param empId 用户的员工ID
     * @return 返回该员工ID对应的设置页所需的信息
     */
    @Override
    public Map<String, Object> getUserSettingsInfoByEmpId(String empId) {
        return saUserInfoMapper.selectUserSettingsInfoByEmpId(empId);
    }

    @Override
    public SaUserInformationEntity getUserInformationByPhone(String phone) {
        return saUserInfoMapper.selectByPhone(phone);
    }
}
