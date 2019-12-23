package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkAccountEntity extends BasePojo implements Serializable {
    private String address;

    private String city;

    private String code;

    private String district;

    private String name;

    private String orgid;

    private String parentid;

    private String province;

    private BigDecimal shoparea;

    private String shopbizcoverage;

    private Integer shopbooth;

    private Integer shopbracket;

    private String shoplevel;

    private String shopprop;

    private Integer shopsalesmanflag;

    private String shopsecondtype;

    private Integer shopshelf;

    private String shopthirdtype;

    private BigDecimal shoptissuecapacity;

    private String shoptype;

    private Integer shopvindaranking;

    private BigDecimal shopvindasales;

    private String status;

    private String type;

    private String contactemail;

    private String contactname;

    private String contactphone;

    private String addrintid;

    private BigDecimal creditexcess;

    private String dealerareaoffice;

    private String dealercityoffice;

    private String dealerprovinceoffice;

    private String intid;

    private String primaryaccntid;

    private Date visitlastdate;

    private Integer needvisitflag;

    private String dealersecondtype;

    private String dealerthirdtype;

    private String virtualclienttype;

    private String createempid;
}