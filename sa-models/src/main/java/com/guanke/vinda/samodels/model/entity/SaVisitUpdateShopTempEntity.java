package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com-guanke-vinda-samodels-model-entity-SaVisitUpdateShopTempEntity")
@Data
public class SaVisitUpdateShopTempEntity extends BaseEntity implements Serializable {
    /**
    * 拜访ID
    */
    @ApiModelProperty(value="拜访ID")
    private String visitId;

    /**
    * 提交人员工ID
    */
    @ApiModelProperty(value="提交人员工ID")
    private String empId;

    /**
    * 地堆数
    */
    @ApiModelProperty(value="地堆数")
    private Integer shopBooth;

    /**
    * 货架数
    */
    @ApiModelProperty(value="货架数")
    private Integer shopShelf;

    /**
    * 端架数
    */
    @ApiModelProperty(value="端架数")
    private Integer shopBracket;

    /**
    * 总地堆数
    */
    @ApiModelProperty(value="总地堆数")
    private Integer shopBoothTotal;

    /**
    * 总货架数
    */
    @ApiModelProperty(value="总货架数")
    private Integer shopShelfTotal;

    /**
    * 总端架数
    */
    @ApiModelProperty(value="总端架数")
    private Integer shopBracketTotal;
}