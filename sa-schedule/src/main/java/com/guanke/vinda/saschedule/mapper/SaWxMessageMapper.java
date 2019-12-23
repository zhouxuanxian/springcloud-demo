package com.guanke.vinda.saschedule.mapper;


import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;
import com.guanke.vinda.samodels.model.entity.SyncLogEntity;

import java.util.Date;
import java.util.List;

public interface SaWxMessageMapper {

    void save(SaWxMessageEntity saWxMessage);

    /**
     * 根据状态和发送时间查询要发送的信息
     * @param status
     * @return
     */

    List<SaWxMessageEntity> findByStatusAndSendTime(Integer status, Date sendTime);



//    List<SaWxMessageEntity> findByStatusAndSendTimes(String status,String param, String sendTimes);
}
