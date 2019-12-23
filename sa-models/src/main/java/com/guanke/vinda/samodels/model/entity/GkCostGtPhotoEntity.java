package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkCostGtPhotoEntity extends BasePojo implements Serializable {
    private String photoKey;

    private String photoNumber;

    private String rowId;

    private String type;
}