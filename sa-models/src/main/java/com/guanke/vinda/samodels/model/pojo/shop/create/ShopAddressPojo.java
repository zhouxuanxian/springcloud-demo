package com.guanke.vinda.samodels.model.pojo.shop.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门店地址实体类
 *
 * @author Nicemorning
 */
@ApiModel("门店地址实体类")
@Data
public class ShopAddressPojo {
    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区/县")
    private String district;

    @ApiModelProperty("详细地址")
    private String address;
}
