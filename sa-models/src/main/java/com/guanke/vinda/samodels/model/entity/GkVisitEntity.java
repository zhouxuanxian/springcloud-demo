package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GkVisitEntity extends BasePojo implements Serializable {
    private String accountId;

    private String issue;

    private String issueDesc;

    private String positionId;

    private String status;

    private Date visitDate;

    private String visitType;

    private String empId;

    private BigDecimal endframeQty;

    private BigDecimal shelfQty;

    private Integer skuQty;

    private BigDecimal stackQty;

    private Integer likeQty;

    private String purpose;

    private String locationAddr;

    private Date visitDateTime;

    private String createdVisitEmpId;

    private String createdVisitPostnId;

    private String cancelReason;

    private Date canceledTime;
}