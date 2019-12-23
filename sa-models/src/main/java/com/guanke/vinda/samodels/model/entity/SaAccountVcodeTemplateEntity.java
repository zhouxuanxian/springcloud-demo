package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value="门店与V码中间关系的临时存储表")
@Data
public class SaAccountVcodeTemplateEntity extends BaseEntity implements Serializable {
    /**
    * V码ID
    */
    @ApiModelProperty(value="V码ID")
    private String vCode;

    /**
    * 门店ID
    */
    @ApiModelProperty(value="门店ID")
    private String accountId;
}