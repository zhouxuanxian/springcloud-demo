package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GkMissionShopEntity extends BasePojo implements Serializable {
    private String missionId;

    private String shopId;

    private Date auditTime;

    private Integer qualifiedFlag;

    private Integer createMsgFlag;

    private String auditEmpId;
}