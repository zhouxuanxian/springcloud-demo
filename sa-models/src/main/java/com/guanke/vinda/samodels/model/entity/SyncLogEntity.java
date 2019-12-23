package com.guanke.vinda.samodels.model.entity;

import lombok.Data;

import java.util.Date;
@Data
public class SyncLogEntity {
    private static final long serialVersionUID = 1L;

    private String model;		//同步对象
    private String recordId;	//对象id
    private String method;		//方法名
    private Date startTime;		//开始时间
    private Date endTime;		//结束时间
    private String status;		//状态
    private String errorMsg;	//错误内容

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
