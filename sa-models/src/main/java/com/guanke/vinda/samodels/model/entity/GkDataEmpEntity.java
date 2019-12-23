package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class GkDataEmpEntity extends BasePojo implements Serializable {
    private Integer competitorCnt;

    private Integer dailyCnt;

    private Integer loginFlag;

    private Integer month;

    private Integer orderVerifyCnt;

    private Integer photosCnt;

    private String positionId;

    private Date recordDate;

    private Integer shopUpdateCnt;

    private Integer visitCnt;

    private Integer year;

    private Integer visitCancelCnt;

    private Integer visitCreatedCnt;

    private Integer visitNonAccntCnt;

    private Integer dailyCreatedCnt;
}