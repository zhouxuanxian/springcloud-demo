package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkCompetitorEntity extends BasePojo implements Serializable {
    private String barCodeNumber;

    private String brand;

    private String keywords;

    private String netweight;

    private String picUrl;

    private String price;

    private String prodDepth;

    private String prodHeight;

    private String prodWidth;

    private String productName;

    private String specification;
}