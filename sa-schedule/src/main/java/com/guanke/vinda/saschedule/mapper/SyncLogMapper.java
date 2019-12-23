package com.guanke.vinda.saschedule.mapper;

import com.guanke.vinda.samodels.model.entity.GkDailyLikeEntity;
import com.guanke.vinda.samodels.model.entity.SyncLogEntity;

import java.util.Date;

public interface SyncLogMapper {


    /**
     * 新增或者修改日志
     * @param sLog
     */
    void save( SyncLogEntity sLog);
}