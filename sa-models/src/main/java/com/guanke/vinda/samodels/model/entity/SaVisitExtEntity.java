package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SaVisitExtEntity extends BaseEntity implements Serializable {
    /**
     * 父表ID
     */
    private String parentId;

    /**
     * 货架位置
     */
    private String shelfLocation;

    /**
     * 堆码位置
     */
    private String stackLocation;

    /**
     * 端架位置
     */
    private String endFrameLocation;

    private Integer softCount;

    private Integer nonCoreCount;

    private Integer hadCoreCount;

    private Integer paperCount;

    private Integer wetCount;

    /**
     * 推广活动描述
     */
    private String expandContent;
}