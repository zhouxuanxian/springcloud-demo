package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-guanke-vinda-samodels-model-entity-SaCompetitorTemplateEntity")
@Data
public class SaCompetitorTemplateEntity extends BaseEntity implements Serializable {
    /**
     * 关联的拜访ID
     */
    @ApiModelProperty(value = "关联的拜访ID")
    private String visitId;

    /**
     * 竞品ID
     */
    @ApiModelProperty(value = "竞品ID")
    private String competitorId;
}