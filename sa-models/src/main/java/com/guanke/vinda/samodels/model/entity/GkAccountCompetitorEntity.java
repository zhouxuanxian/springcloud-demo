package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GkAccountCompetitorEntity extends BasePojo implements Serializable {
    private String accountId;

    private String barCodeNumber;

    private String competitorId;

    private String empId;

    private Date endDateOfPmt;

    private String positionId;

    private BigDecimal promotionPrice;

    private String promotionType;

    private BigDecimal salesPrice;

    private Date startDateOfPmt;

    private String visitId;

    private Date writeTime;
}