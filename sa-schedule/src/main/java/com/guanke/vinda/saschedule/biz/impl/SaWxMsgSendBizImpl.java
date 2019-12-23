package com.guanke.vinda.saschedule.biz.impl;

import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;
import com.guanke.vinda.saschedule.biz.DataRecordServiceBiz;
import com.guanke.vinda.saschedule.biz.SaWxMsgSendBiz;
import com.guanke.vinda.saschedule.mapper.SaWxMessageMapper;
import com.guanke.vinda.saschedule.service.SyncLogService;
import com.guanke.vinda.saschedule.utils.DateUtil;

import com.guanke.vinda.saschedule.utils.QueryParam;
import com.guanke.vinda.saschedule.utils.QuerySpec;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaWxMsgSendBizImpl implements SaWxMsgSendBiz {

    final String MODEL = "SaWxMessage";		//操作的对象
    final String METHOD = "经销宝微信消息发送";		//方法

    @Resource
    SaWxMessageMapper saWxMessageMapper;
    @Resource
    SyncLogService syncLogService;
    @Resource
    DataRecordServiceBiz dataRecordService;

    @Transactional
    public void wxMsgSend(){

        System.out.println(METHOD + "-开始："+ DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss"));
        int successSeq = 0;

        //第一步：查询需要发送消息的微信消息记录
        List<SaWxMessageEntity> list = this.queryNeedSendWxMessage(MODEL, METHOD);
        //第三步：生成msg
        for(SaWxMessageEntity saWxMessage:list){
            Map<String, Object> result = dataRecordService.wxMsgSendAndUpd(saWxMessage, MODEL, METHOD);
            if("success".equals((String)result.get("status"))){
                successSeq++;
            }
        }

        System.out.println(METHOD + "-结束：" + DateUtil.convertDateToString(new Date(), "yyyyMMddHHmmss。共：" + list.size() + "，成功：" + successSeq));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<SaWxMessageEntity> queryNeedSendWxMessage(String model,String method) {
        try {
            List<SaWxMessageEntity> result = new ArrayList<SaWxMessageEntity>();

            //查询未发送的
              List<SaWxMessageEntity> li1 = saWxMessageMapper.findByStatusAndSendTime(0,new Date());
            if(li1 != null && li1.size() > 0) result.addAll(li1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, null, method, e.getMessage(), null);
            return null;
        }
    }
}
