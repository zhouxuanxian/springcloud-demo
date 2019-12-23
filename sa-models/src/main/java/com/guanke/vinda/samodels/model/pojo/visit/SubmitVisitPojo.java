package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 拜访记录提交实体类
 *
 * @author Nicemorning
 */
@ApiModel("提交拜访记录")
@Data
public class SubmitVisitPojo {
    @ApiModelProperty("拜访ID")
    private String id;

    @ApiModelProperty("如果是非拜访事项，则将图片的外链KEY传入该数组")
    private List<String> photos;

    @ApiModelProperty("如果是非拜访事项，则拜访描述传入该字段")
    private String issueDesc;

    @ApiModelProperty("拜访定位地址")
    private String locationAddr;

    @ApiModelProperty("如果是提交，则传入`SUBMIT`；如果是暂存，则传入`DRAFT`")
    private String operation;

    @ApiModelProperty("拜访事项")
    private List<String> purposes;

    @ApiModelProperty("【以下内容仅拜访事项需要提交】如果是拜访事项，则传入`RealAccount`'")
    private String visitType;

    @ApiModelProperty("维达地堆数")
    private String stackQty;

    @ApiModelProperty("总地堆数")
    private String shopBooth;

    @ApiModelProperty("维达货架数")
    private String shelfQty;

    @ApiModelProperty("总货架数")
    private String shopShelf;

    @ApiModelProperty("维达端架数")
    private String endFrameQty;

    @ApiModelProperty("总端架数")
    private String shopBracket;

    @ApiModelProperty("地堆图片")
    private List<String> stackPhotos;

    @ApiModelProperty("货架图片")
    private List<String> shelfPhotos;

    @ApiModelProperty("端架图片")
    private List<String> endFramePhotos;

    @ApiModelProperty("货架描述")
    private String shelfContent;

    @ApiModelProperty("地堆描述")
    private String stackContent;

    @ApiModelProperty("端架描述")
    private String endFrameContent;

    @ApiModelProperty("货架位置")
    private String shelfLocation;

    @ApiModelProperty("地堆位置")
    private String stackLocation;

    @ApiModelProperty("端架位置")
    private String endFrameLocation;

    @ApiModelProperty("促销内容")
    private PromotionPojo promotion;

    @ApiModelProperty("推广活动")
    private ExpandPojo expand;

    @ApiModelProperty("竞品列表")
    private List<CompetitorPojo> competitors;

    @ApiModelProperty("V码列表")
    private List<VCodePojo> vCodes;
}
