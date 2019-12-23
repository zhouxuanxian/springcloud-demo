package com.guanke.vinda.samodels.model.pojo.visit;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 非拜访事项详情查看实体
 *
 * @author Nicemorning
 */
@NoArgsConstructor
@ApiModel("非拜访事项查看详情实体")
@Data
public class NonAccountVisitDetailPojo {
    @ApiModelProperty("拜访ID")
    private String id;

    @ApiModelProperty("拜访时间")
    private String visitDate;

    @ApiModelProperty("定位地址")
    private String locationAddr;

    @ApiModelProperty("拜访目的")
    private String purpose;

    @ApiModelProperty("文本描述")
    private String content;

    @ApiModelProperty("图片主机")
    private String prefix;

    @ApiModelProperty("图片列表")
    private List<String> photos;

    @ApiModelProperty("拜访状态")
    private String status;
}
