package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaUserTimeRecodeEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 用户职位ID
    */
    private String positionId;

    /**
    * 用户手机号
    */
    private String phone;

    /**
    * 用户在CRM中所对应的RowId
    */
    private String rowId;

    /**
    * 用户上一次登陆时间
    */
    private Date lastLogin;

    /**
    * 用户上一次查看工作日报中的消息时间
    */
    private Date dailyMessageCheck;
}