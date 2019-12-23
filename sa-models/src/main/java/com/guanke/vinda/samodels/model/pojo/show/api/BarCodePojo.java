package com.guanke.vinda.samodels.model.pojo.show.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BarCodePojo {

    private String id;
    private String showapiResError;
    private int showapiResCode;
    private String showapiResId;
    private ShowapiResBody showapiResBody;

}
