package com.guanke.vinda.samodels.model.pojo.show.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ShowapiResBody {

    private String sptmImg;
    private String spec;
    private String remark;
    private int showapiFeeCode;
    private String img;
    private String code;
    private String ycg;
    private String manuName;
    private String retCode;
    private boolean flag;
    private String price;
    private String trademark;
    private String manuAddress;
    private String note;
    private String goodsName;
    private String goodsType;
    private List<?> imgList;
}
