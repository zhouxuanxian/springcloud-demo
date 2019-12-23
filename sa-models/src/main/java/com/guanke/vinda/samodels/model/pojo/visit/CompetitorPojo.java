package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 竞品实体
 *
 * @author Nicemorning
 */
@ApiModel("竞品实体类")
@Data
public class CompetitorPojo {
    @ApiModelProperty("竞品ID")
    private String id;

    @ApiModelProperty("条形码值")
    private String barCode;

    @ApiModelProperty("当前售价")
    private Double price;

    @ApiModelProperty("促销方式")
    private String promotionAction;

    @ApiModelProperty("促销开始时间")
    private String promotionStartTime;

    @ApiModelProperty("促销结束时间")
    private String promotionEndTime;

    @ApiModelProperty("品牌")
    private String brand;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("规格型号")
    private String norms;

    @ApiModelProperty("宽度")
    private Double width;

    @ApiModelProperty("高度")
    private Double height;

    @ApiModelProperty("深度")
    private Double depth;

    @ApiModelProperty("重量")
    private Double weight;

    @ApiModelProperty("图片列表")
    private List<String> photos;

    @ApiModelProperty("扫码得到的图片")
    private String picUrl;

}
