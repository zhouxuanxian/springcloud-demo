package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkCompetitorPhotoEntity extends BasePojo implements Serializable {
    private String competitorId;

    private String empId;

    private String photoKey;
}