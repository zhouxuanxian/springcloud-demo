package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GkAccountSalesReportEntity extends BasePojo implements Serializable {

    private String accountId;

    private String empId;

    private String status;

    private Date submitTime;

    private String positionId;
}