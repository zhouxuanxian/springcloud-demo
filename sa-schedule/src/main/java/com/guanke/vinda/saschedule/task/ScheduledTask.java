package com.guanke.vinda.saschedule.task;

import com.guanke.vinda.saschedule.biz.SaWxMsgSendBiz;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ScheduledTask {
    @Autowired
    SaWxMsgSendBiz saWxMsgSendBiz;


    @Scheduled(cron = "0/10 * * * * ?")    //每3分钟执行一次
    public void sendWxMessage() {
       try {

            saWxMsgSendBiz.wxMsgSend();

        } catch (Exception e) {
           e.printStackTrace();
        } finally {

            }
        }
}
