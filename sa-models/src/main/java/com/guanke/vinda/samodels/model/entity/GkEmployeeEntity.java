package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkEmployeeEntity extends BasePojo {
    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean credentialsExpired;

    private String deptId;

    private Boolean enabled;

    private String name;

    private String orgId;

    private String password;

    private String phoneNumber;

    private String status;

    private String photoUrl;

    private String intId;

    private Integer likeQty;

    private String login;

    private String priShopId;

    private String guideMark;

    private Date lastLoginTime;

    private Boolean superAdminFlag;
}