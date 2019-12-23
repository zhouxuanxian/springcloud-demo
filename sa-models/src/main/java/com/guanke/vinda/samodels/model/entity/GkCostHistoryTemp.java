package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * author:ldm
 * date:14:54 2019/11/21
 * describe: 默认描述
 */
@Data
public class GkCostHistoryTemp extends BasePojo implements Serializable {
    private Date approDatetime;

    private String approEmpId;

    private String approEmpPosId;

    private String approRemark;

    private String reimId;
}