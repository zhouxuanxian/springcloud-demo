package com.guanke.vinda.samodels.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 图片上传实体类
 *
 * @author Nicemorning
 */
@ApiModel("图片上传实体")
@Data
public class PhotoUploadPojo {
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片类型，请填写功能模块标题")
    private String type;

    @ApiModelProperty("图片的微信临时资源ID")
    private List<String> serverIds;
}
