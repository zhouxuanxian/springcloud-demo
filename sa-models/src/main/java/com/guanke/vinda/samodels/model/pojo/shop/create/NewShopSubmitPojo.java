package com.guanke.vinda.samodels.model.pojo.shop.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 新建门店提交实体类
 *
 * @author Nicemorning
 */
@ApiModel("新建门店提交实体类")
@Data
public class NewShopSubmitPojo {
    @ApiModelProperty("门店名称")
    private String accountName;

    @ApiModelProperty("所属经销商ID")
    private String dealerId;

    @ApiModelProperty("客户团队ID")
    private String customerTeamId;

    @ApiModelProperty("门店等级")
    private String accountLevel;

    @ApiModelProperty("门店特性")
    private String accountFeature;

    @ApiModelProperty("门店地址相关信息")
    private ShopAddressPojo address;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("门店面积")
    private String accountArea;

    @ApiModelProperty("纸品容量")
    private String paperVolume;

    @ApiModelProperty("维达销量")
    private String vindaSales;

    @ApiModelProperty("维达排名")
    private String vindaRank;

    @ApiModelProperty("门店资源相关信息")
    private ShopResourcePojo accountResource;
}
