package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkVisitLikeEmpEntity extends BasePojo implements Serializable {
    private String empId;

    private String visitId;

    private Integer readFlag;
}