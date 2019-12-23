package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "竞品信息图片中间状态临时表")
@Data
public class SaCompetitorPhotoTempEntity extends BaseEntity implements Serializable {
    /**
     * 上传人的员工ID
     */
    @ApiModelProperty(value = "上传人的员工ID")
    private String empId;

    /**
     * 图片外链地址
     */
    @ApiModelProperty(value = "图片外链地址")
    private String photoKey;

    /**
     * 临时竞品信息表ID
     */
    @ApiModelProperty(value = "临时竞品信息表ID")
    private String tempCompetitorId;
}