package com.guanke.vinda.saschedule.biz;

import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;

import java.util.List;
/*
 * 微信消息发送工作流
 */
public interface SaWxMsgSendBiz {
    /**
     * 发送微信消息
     */
    void wxMsgSend();

    /**
     * 查询需要发送的消息
     * @param model
     * @param method
     * @return
     */
    List<SaWxMessageEntity> queryNeedSendWxMessage(String model, String method);

}
