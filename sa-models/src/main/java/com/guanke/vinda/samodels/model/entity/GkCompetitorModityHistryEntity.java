package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author Nicemorning
 */
@ApiModel(value = "com-guanke-vinda-samodels-model-entity-GkCompetitorModityHistryEntity")
@Data
public class GkCompetitorModityHistryEntity extends BasePojo implements Serializable {
    @ApiModelProperty(value = "null")
    private String barCodeNumber;

    @ApiModelProperty(value = "null")
    private String brand;

    @ApiModelProperty(value = "null")
    private String competitorId;

    @ApiModelProperty(value = "null")
    private String netweight;

    @ApiModelProperty(value = "null")
    private String prodDepth;

    @ApiModelProperty(value = "null")
    private String prodHeight;

    @ApiModelProperty(value = "null")
    private String prodWidth;

    @ApiModelProperty(value = "null")
    private String productName;

    @ApiModelProperty(value = "null")
    private String specification;
}