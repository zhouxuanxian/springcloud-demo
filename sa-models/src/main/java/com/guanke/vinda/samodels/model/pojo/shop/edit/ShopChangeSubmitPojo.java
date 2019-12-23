package com.guanke.vinda.samodels.model.pojo.shop.edit;

import com.guanke.vinda.samodels.model.pojo.shop.create.ShopAddressPojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门店信息更改提交实体类
 *
 * @author Nicemorning
 */
@ApiModel("门店信息更改提交实体类")
@Data
public class ShopChangeSubmitPojo {
    @ApiModelProperty("门店ID")
    private String accountId;

    @ApiModelProperty("门店名称")
    private String accountName;

    @ApiModelProperty("地址相关信息")
    private ShopAddressPojo addressPojo;

    @ApiModelProperty("所属经销商ID")
    private String dealerId;

    @ApiModelProperty("门店状态")
    private String accountStatus;

    @ApiModelProperty("二级分类")
    private String secondType;

    @ApiModelProperty("三级分类")
    private String thirdType;

    @ApiModelProperty("业务是否覆盖门店")
    private String coverAccount;

    @ApiModelProperty("门店等级")
    private String accountLevel;

    @ApiModelProperty("门店特性")
    private String accountFeature;

    @ApiModelProperty("维达排名")
    private String vindaRank;

    @ApiModelProperty("门店面积")
    private String accountArea;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;
}
