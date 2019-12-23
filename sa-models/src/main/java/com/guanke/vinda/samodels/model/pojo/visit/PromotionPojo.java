package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 促销实体类
 *
 * @author Nicemorning
 */
@ApiModel("促销实体")
@Data
public class PromotionPojo {
    @ApiModelProperty("软抽促销单品数")
    private Integer promotionSoftCount;

    @ApiModelProperty("无芯卫卷促销单品数")
    private Integer promotionNonCoreCount;

    @ApiModelProperty("有芯卫卷促销单品数")
    private Integer promotionHadCoreCount;

    @ApiModelProperty("手帕促销单品数")
    private Integer promotionPaperCount;

    @ApiModelProperty("湿巾促销单品数")
    private Integer promotionWetCount;

    @ApiModelProperty("促销图片")
    private List<String> promotionPhotos;
}
