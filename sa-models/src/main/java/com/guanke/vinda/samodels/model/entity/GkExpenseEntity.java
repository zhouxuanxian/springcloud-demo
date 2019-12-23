package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GkExpenseEntity extends BasePojo implements Serializable {
    private String activityId;

    private String dealerId;

    private String expenseNumber;

    private String intId;

    private String planId;

    private String productSectionIntId;

    private String shopId;

    private String status;

    private String type;

    private String activityNumber;

    private String applyEmpId;

    private Date applyTime;

    private BigDecimal applyTotle;

    private BigDecimal dealerCost;

    private Integer displayQty;

    private BigDecimal expenseRatio;

    private BigDecimal foreseeSales;

    private Integer month;

    private String name;

    private String orgId;

    private String positionId;

    private String productBrand;

    private String productSectionId;

    private String productSubjects;

    private String productVcode;

    private BigDecimal promotionSales;

    private Integer year;

    private String vCode;

    private Date approveTime;

    private String oaTheme;
}