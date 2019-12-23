package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkAccountChangeEntity extends BasePojo implements Serializable {

    private String accntId;

    private String address;

    private String city;

    private String district;

    private String name;

    private String parentId;

    private String province;

    private String shopBizCoverage;

    private String shopLevel;

    private String shopSecondType;

    private String shopThirdType;

    private String submitEmpId;

    private Date submitTime;

    private String intId;

    private String status;

    private String submitStatus;

    private Boolean otherSysFlag;
}