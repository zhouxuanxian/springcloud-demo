package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkWsLogEntity extends BasePojo implements Serializable {
    private String errmessage;

    private String methodname;

    private Integer times;

    private Long usedtime;

    private String recordid;
}