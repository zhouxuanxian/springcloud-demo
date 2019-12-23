package com.guanke.vinda.saschedule.biz.impl;

import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;
import com.guanke.vinda.samodels.model.utils.config.Constants;
import com.guanke.vinda.saschedule.biz.DataRecordServiceBiz;
import com.guanke.vinda.saschedule.biz.KAcostVerifyBiz;
import com.guanke.vinda.saschedule.mapper.GkEmployeeMapper;
import com.guanke.vinda.saschedule.service.SyncLogService;
import com.guanke.vinda.saschedule.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * KA费用核销提醒消息发送
 */
@Service
public class KAcostVerfyBizImpl implements KAcostVerifyBiz {

    final String MODEL = "Message";		//操作的对象
    final String METHOD = "KA费用核销提醒消息生成";		//方法

    @Resource
    GkEmployeeMapper gkEmployeeMapper;
    @Resource
    SyncLogService syncLogService;
    @Resource
    DataRecordServiceBiz dataRecordService;
    @Transactional
    public void needKASendCheck() {

        System.out.println(METHOD + "-开始："+DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss"));
        int successSeq = 0;
        Map<String,Object> qp = new HashMap<String, Object>();
        //第一步：查询需要发送消息的微信消息记录
        List<Map<String, Object>> list = this.queryNeedSendMsg(qp, MODEL, METHOD);
        //第二步：生成msg
        for(Map<String, Object> map:list){
            SaWxMessageEntity saWxMessage = new SaWxMessageEntity();
            saWxMessage.setAgentId(Constants.WX_AGENT_ID);
            saWxMessage.setToUser(map.get("wxUserId").toString());
            saWxMessage.setMsgType("text");
            saWxMessage.setToParty("");
            saWxMessage.setToTag("");

            saWxMessage.setRecordId(map.get("Row_Id").toString());
            if("".equals(map.get("Name")))
                saWxMessage.setContent(map.get("contents").toString());
            else
                saWxMessage.setContent("您有"+map.get("System")+map.get("Name")+"的KA费用核销待处理！");
            Map<String, Object> result = dataRecordService.wxMsgSendAndUpd(saWxMessage,MODEL, METHOD);
            if("success".equals((String)result.get("status"))){
                successSeq++;
            }
        }

        System.out.println(METHOD + "-结束：" + DateUtil.convertDateToString(new Date(), "yyyyMMddHHmmss。共：" + list.size() + "，成功：" + successSeq));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, Object>> queryNeedSendMsg(Map<String, Object> qp, String model, String method) {
        try {
            List<Map<String, Object>> list = gkEmployeeMapper.queryKaCostVerify();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, null, method, e.getMessage(), null);
            return null;
        }
    }
}
