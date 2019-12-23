package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户信息表
 *
 * @author Nicemorning
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaUserInformationEntity extends BaseEntity {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户注册时间（首次登陆时间）
     */
    private Date registerTime;
    /**
     * 用户状态，有效为0，失效为1
     */
    private Integer status;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 用户CRM人员表中对应的RowID
     */
    private String rowId;
    /**
     * 对应CRM的职位ID
     */
    private String positionId;
    /**
     * 用户在CRM中对应的EMPID
     */
    private String empId;
}
