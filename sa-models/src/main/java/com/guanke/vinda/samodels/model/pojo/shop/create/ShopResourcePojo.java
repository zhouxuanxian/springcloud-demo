package com.guanke.vinda.samodels.model.pojo.shop.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门店资源信息实体类
 *
 * @author Nicemorning
 */
@ApiModel("门店资源信息实体类")
@Data
public class ShopResourcePojo {
    @ApiModelProperty("货架")
    private String shelfCount;

    @ApiModelProperty("地堆")
    private String blockCount;

    @ApiModelProperty("端架")
    private String endFrameCount;
}
