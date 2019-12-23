package com.guanke.vinda.samodels.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * author:ldm
 * date:15:06 2019/11/18
 * describe: 默认描述
 */
@Data
public class CxGtReimEntity implements Serializable {
    private String rowId;

    private Date created;

    private String createdBy;

    private Date lastUpd;

    private String lastUpdBy;

    private Long modificationNum;

    private String conflictId;

    private BigDecimal aduShelfNum;

    private BigDecimal aduVdShelfNum;

    private String columnStatus;

    private Date dbLastUpd;

    private BigDecimal djCheckNum;

    private BigDecimal djSendNum;

    private BigDecimal dryShelfNum;

    private BigDecimal dryVdShelfNum;

    private BigDecimal hjCheckNum;

    private BigDecimal hjSendNum;

    private String lastMonSales;

    private Date reimTime;

    private String storeTypeFlg;

    private BigDecimal tamponShelfNum;

    private BigDecimal tamponVdShelfNum;

    private BigDecimal tgCheckNum;

    private BigDecimal tgSendNum;

    private BigDecimal wetShelfNum;

    private BigDecimal wetVdShelfNum;

    private String aduVdShelfPos;

    private String applyNum;

    private String applyPhone;

    private String applyPosId;

    private String approStatus;

    private String checkProblem;

    private String checkStoreType;

    private String cityApproResult;

    private String creatorPosId;

    private String dbLastUpdSrc;

    private String diffContant;

    private String diffType;

    private String djListPos;

    private String dryVdShelfPos;

    private String endExecuteTime;

    private String expenseType;

    private String groundingType;

    private String gtExpVerfCode;

    private String hjListPos;

    private String positionInfo;

    private String promApproResult;

    private String provApproResult;

    private String reimStatus;

    private String startExecuteTime;

    private String storeId;

    private String submitNum;

    private String tamponVdShelfPos;

    private String tgPos;

    private String wetVdShelfPos;

    private Integer syncflag;

    private String sendStoreType;

    private static final long serialVersionUID = 1L;
}