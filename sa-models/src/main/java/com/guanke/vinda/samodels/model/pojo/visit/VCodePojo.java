package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * V码实体类
 *
 * @author Nicemorning
 */
@ApiModel("V码实体")
@Data
public class VCodePojo {
    @ApiModelProperty("V码")
    private String id;

    @ApiModelProperty("产品分类")
    private String category;

    @ApiModelProperty("库存数")
    private Integer stock;

    @ApiModelProperty("订单数")
    private Integer order;

    @ApiModelProperty("价格")
    private Integer price;
}
