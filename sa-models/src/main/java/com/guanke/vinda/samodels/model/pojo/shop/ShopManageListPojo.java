package com.guanke.vinda.samodels.model.pojo.shop;

import lombok.Data;

/**
 * @author Nicemorning
 */
@Data
public class ShopManageListPojo {
    private String id;
    private String intId;
    private String name;
    private String province;
    private String city;
    private String district;
    private String address;
    private String type;
    private String shopLevel;
    private Integer needVisit;
    private String parentName;
    private String shopFeature;

}
