package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkDailyLikeEntity extends BasePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日报ID
     */
    private String dailyId;

    /**
     * 点赞人ID
     */
    private String empId;

    /**
     * 阅读标记，默认为0
     */
    private Integer readFlag;
}