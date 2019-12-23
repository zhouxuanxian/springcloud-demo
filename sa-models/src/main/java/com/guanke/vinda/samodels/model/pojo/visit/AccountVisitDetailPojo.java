package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 拜访事项查看详情实体
 *
 * @author Nicemorning
 */
@NoArgsConstructor
@Data
@ApiModel("门店拜访事项详情实体")
public class AccountVisitDetailPojo {
    @ApiModelProperty("拜访ID")
    private String id;

    @ApiModelProperty("门店名称")
    private String accountName;

    @ApiModelProperty("门店地址")
    private String accountAddress;

    @ApiModelProperty("门店ID")
    private String accountId;

    @ApiModelProperty("拜访定位地址")
    private String locationAddr;

    @ApiModelProperty("拜访人姓名")
    private String empName;

    @ApiModelProperty("拜访人职位")
    private String empPosition;

    @ApiModelProperty("拜访日期")
    private String visitDate;

    @ApiModelProperty("维达地堆数")
    private String stackQty;

    @ApiModelProperty("总地堆数")
    private String shopBooth;

    @ApiModelProperty("维达地堆数，如果是负数则代表下降，如果是正数则代表上升，如果为零则持平")
    private String stackCompareCount;

    @ApiModelProperty("维达产品V码个数，如果是负数则代表下降，如果是正数则代表上升，如果为零则持平")
    private String vCodeCount;

    @ApiModelProperty("维达货架数")
    private String shelfQty;

    @ApiModelProperty("总货架数")
    private String shopShelf;

    @ApiModelProperty("维达货架数，如果是负数则代表下降，如果是正数则代表上升，如果为零则持平")
    private String shelfCompareCount;

    @ApiModelProperty("维达端架数")
    private String endFrameQty;

    @ApiModelProperty("总端架数")
    private String shopBracket;

    @ApiModelProperty("维达端架数，如果是负数则代表下降，如果是正数则代表上升，如果为零则持平")
    private String endFrameCompareCount;

    @ApiModelProperty("图片主机")
    private String prefix;

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

    @ApiModelProperty("拜访状态")
    private String status;
}
