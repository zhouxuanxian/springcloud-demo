package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkAccountVcodeEntity extends BasePojo implements Serializable {
    private String vCode;

    private String accountId;
}