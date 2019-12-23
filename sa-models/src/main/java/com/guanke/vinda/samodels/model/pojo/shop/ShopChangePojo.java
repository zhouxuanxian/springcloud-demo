package com.guanke.vinda.samodels.model.pojo.shop;

import lombok.Data;

/**
 * 门店变更详情实体类
 *
 * @author Nicemorning
 */
@Data
public class ShopChangePojo {
    private String id;
    private String shopName;
    private String submitName;
    private String submitTime;
    private String otherSysFlag;
    private String newName;
    private String parentName;
    private String shopLevelValue;
    private String shopSecondTypeValue;
    private String shopThirdTypeValue;
    private String shopBizCoverageValue;
    private String province;
    private String city;
    private String district;
    private String address;
    private String statusValue;
}
