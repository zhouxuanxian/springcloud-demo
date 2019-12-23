package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkListOfValueEntity extends BasePojo implements Serializable {
    private Boolean active;

    private String code;

    private String comments;

    private String constraintValue;

    private String intId;

    private String parentId;

    private Integer selectedFlag;

    private Integer seq;

    private String type;

    private String value;
}