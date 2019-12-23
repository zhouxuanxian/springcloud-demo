package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GkStockOrderEntity extends BasePojo implements Serializable {
    private String vCode;

    private String accountId;

    private String empId;

    private Integer orderQty;

    private String positionId;

    private Integer stockQty;

    private String visitId;

    private Date writeTime;

    private BigDecimal price;
}