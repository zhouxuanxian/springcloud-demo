package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SaVisitContentEntity extends BaseEntity implements Serializable {
    /**
    * 关联GK_VISIT表的ID
    */
    private String gkVisitId;

    /**
    * 货架描述
    */
    private String shelfContent;

    /**
    * 地堆描述
    */
    private String stackContent;

    /**
    * 端架描述
    */
    private String endFrameContent;
}