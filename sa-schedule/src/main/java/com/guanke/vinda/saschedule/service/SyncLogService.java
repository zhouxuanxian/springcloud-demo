package com.guanke.vinda.saschedule.service;

import com.guanke.vinda.samodels.model.entity.SyncLogEntity;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.saschedule.mapper.SyncLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("syncLogService")
public class SyncLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncLogService.class);

    @Resource
    SyncLogMapper syncLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public void saveError(String model, String recordId, String method, String errorMsg, Date startTime) {
        if (!StringUtil.isNull(errorMsg) && errorMsg.length() >= 1000)
            errorMsg = errorMsg.substring(0, 999);

        SyncLogEntity sLog = new SyncLogEntity();
        sLog.setModel(model);
        sLog.setRecordId(recordId);
        sLog.setMethod(method);
        sLog.setErrorMsg(errorMsg);
        sLog.setStartTime(startTime);
        sLog.setEndTime(new Date());
        sLog.setStatus("error");
        try {
            syncLogMapper.save(sLog);
        } catch (Exception e) {
            LOGGER.info("Save message error throw an exception: " + e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public void saveSuccess(String model, String recordId, String method, String errorMsg, Date startTime) {
        try {
            if (!StringUtil.isNull(errorMsg) && errorMsg.length() >= 1000)
                errorMsg = errorMsg.substring(0, 999);

            SyncLogEntity sLog = new SyncLogEntity();
            sLog.setModel(model);
            sLog.setRecordId(recordId);
            sLog.setMethod(method);
            sLog.setErrorMsg(errorMsg);
            sLog.setStartTime(startTime);
            sLog.setEndTime(new Date());
            sLog.setStatus("success");

            syncLogMapper.save(sLog);

        } catch (Exception e) {
            LOGGER.info("Save message success throw an exception: " + e.getMessage());
        }
    }
}
