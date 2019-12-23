package com.guanke.vinda.samodels.model.pojo.visit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 推广活动实体类
 *
 * @author Nicemorning
 */
@ApiModel("推广实体")
@Data
public class ExpandPojo {
    @ApiModelProperty("推广活动类型")
    private String expandType;

    @ApiModelProperty("推广活动图片")
    private List<String> expandPhotos;
}
