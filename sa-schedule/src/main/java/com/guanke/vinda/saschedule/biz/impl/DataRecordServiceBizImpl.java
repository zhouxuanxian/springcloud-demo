package com.guanke.vinda.saschedule.biz.impl;

import com.guanke.vinda.samodels.model.entity.SaWxMessageEntity;
import com.guanke.vinda.samodels.model.entity.WxCpMessageSendResultEntity;
import com.guanke.vinda.samodels.model.utils.StringUtil;
import com.guanke.vinda.samodels.model.utils.config.Constants;
import com.guanke.vinda.saschedule.biz.DataRecordServiceBiz;
import com.guanke.vinda.saschedule.mapper.GkEmployeeMapper;
import com.guanke.vinda.saschedule.mapper.SaWxMessageMapper;
import com.guanke.vinda.saschedule.pojo.*;
import com.guanke.vinda.saschedule.service.SyncLogService;
import com.guanke.vinda.saschedule.service.WebChatService;
import com.guanke.vinda.saschedule.utils.UuidUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class DataRecordServiceBizImpl implements DataRecordServiceBiz {

    final String MODULE = "CostApply";        // 模块（上传附件使用）【费用】


    @Resource
    SyncLogService syncLogService;
    @Resource
    GkEmployeeMapper gkEmployeeMapper;
    @Resource
    SaWxMessageMapper saWxMessageMapper;
    @Resource
    WebChatService webChatService;//发送微信消息


    /*
     * 发送微信消息并回写结果
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object>wxMsgSendAndUpd(SaWxMessageEntity saWxMessage, String model, String method) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "success");
        try {
            String msgtype = saWxMessage.getMsgType();
            saWxMessage.setStatus(1);

            //文本消息
            if ("text".equals(msgtype)) {

                TextMessage textMessage=new TextMessage();
                textMessage.setAgentid(saWxMessage.getAgentId()); // 企业号应用ID
                textMessage.setTouser(saWxMessage.getToUser());
                textMessage.setToparty(saWxMessage.getToParty());
                textMessage.setTotag(saWxMessage.getToTag());
                textMessage.setMsgtype(saWxMessage.getMsgType());
                Text text=new Text();
                text.setContent(saWxMessage.getContent());
                textMessage.setText(text);
                WxCpMessageSendResultEntity wxResult = webChatService.sendMessage(textMessage);
                if (wxResult != null) {
                    if (!StringUtil.isNull(wxResult.getInvalidUser()) ||
                            !StringUtil.isNull(wxResult.getInvalidParty()) ||
                            !StringUtil.isNull(wxResult.getInvalidTag())) {
                        throw new Exception(wxResult.toString());
                    }
                    saWxMessage.setWxResult(wxResult.toString());
                } else {
                    throw new Exception("系统异常");
                }
            }
          if("textcard".equals(msgtype)){
                TextCardMessage textCardMessage=new TextCardMessage();
                textCardMessage.setAgentid(saWxMessage.getAgentId()); // 企业号应用ID
                textCardMessage.setTouser(saWxMessage.getToUser());
                textCardMessage.setToparty(saWxMessage.getToParty());
                textCardMessage.setTotag(saWxMessage.getToTag());
                textCardMessage.setMsgtype(saWxMessage.getMsgType());
                TextCard textCard=new TextCard();
                textCard.setUrl(saWxMessage.getUrl());
                textCard.setDescription(saWxMessage.getDescription());
                textCard.setTitle(saWxMessage.getTitle());
                textCard.setBtntxt(saWxMessage.getBtnTxt());
                textCardMessage.setTextcard(textCard);
                WxCpMessageSendResultEntity wxResult = webChatService.sendMessage(textCardMessage);
                    if (wxResult != null) {
                        if (!StringUtil.isNull(wxResult.getInvalidUser()) ||
                                !StringUtil.isNull(wxResult.getInvalidParty()) ||
                                !StringUtil.isNull(wxResult.getInvalidTag())) {
                            throw new Exception(wxResult.toString());
                        }
                        saWxMessage.setWxResult(wxResult.toString());
                } else {
                    throw new Exception("系统异常");
                }
            }
            if("image".equals(msgtype)){
                ImgMessage imgMessage=new ImgMessage();
                imgMessage.setAgentid(saWxMessage.getAgentId()); // 企业号应用ID
                imgMessage.setTouser(saWxMessage.getToUser());
                imgMessage.setToparty(saWxMessage.getToParty());
                imgMessage.setTotag(saWxMessage.getToTag());
                imgMessage.setMsgtype(saWxMessage.getMsgType());
                Media media=new Media();
                media.setMedia_id(saWxMessage.getMediaId());
                imgMessage.setImage(media);
                WxCpMessageSendResultEntity wxResult = webChatService.sendMessage(imgMessage);
                if (wxResult != null) {
                    if (!StringUtil.isNull(wxResult.getInvalidUser()) ||
                            !StringUtil.isNull(wxResult.getInvalidParty()) ||
                            !StringUtil.isNull(wxResult.getInvalidTag())) {
                        throw new Exception(wxResult.toString());
                    }
                    saWxMessage.setWxResult(wxResult.toString());
                } else {
                    throw new Exception("系统异常");
                }
            }
            saWxMessage.setUpdatedTime(new Date());
            saWxMessage.setUpdatedBy(Constants.DEFAULT_USER_ID);
            saWxMessage.setVersion(saWxMessage.getVersion()+1);

            saWxMessageMapper.save(saWxMessage);
        } catch (Exception e) {
            e.printStackTrace();
            saWxMessage.setStatus(2);

            saWxMessage.setWxResult(e.getMessage());

            saWxMessage.setUpdatedTime(new Date());
            saWxMessage.setUpdatedBy(Constants.DEFAULT_USER_ID);
            saWxMessage.setVersion(saWxMessage.getVersion()+1);
            saWxMessageMapper.save(saWxMessage);


            syncLogService.saveError(model, null, method, "异常：微信消息发送异常" + e.getMessage(), null);
            result.put("status", "fail");
        }

        return result;

    }


    /*
     * 创建日报提醒消息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> createWxMessageByDailyPostn(Map<String, Object> map,String model,String method) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "success");
        String empId = (String) map.get("empId");
        String empNo = (String) map.get("login");
        try {
            SaWxMessageEntity saWxMessage = new SaWxMessageEntity();
            saWxMessage.setToUser(empNo);
            saWxMessage.setAgentId(Constants.WX_AGENT_ID);
            saWxMessage.setMsgType("text");
            saWxMessage.setContent("您今日尚未提交工作日报，请及时完成并提交！");
            saWxMessage.setCreatedBy(Constants.DEFAULT_USER_ID);
            Date date=new Date();
            saWxMessage.setCreatedTime(date);
            saWxMessage.setUpdatedBy(Constants.DEFAULT_USER_ID);
            saWxMessage.setUpdatedTime(date);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 17);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            Date m6= c.getTime();
            saWxMessage.setVersion(0);
            saWxMessage.setSendTime(m6);
            saWxMessage.setSaWxMessageId(UuidUtils.compressedUuid());

            saWxMessageMapper.save(saWxMessage);
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, empId, method, e.getMessage(), null);
            result.put("status", "fail");
        }
        return result;
    }


    /*
     * 创建尚未添加拜访计划的 提醒消息
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public Map<String, Object> createWxMessageByNoVisitPostn(Map<String, Object> map,String model,String method) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "success");
        String empId = (String) map.get("empId");
        String empNo = (String) map.get("login");
        try {
            SaWxMessageEntity saWxMessage = new SaWxMessageEntity();
            saWxMessage.setToUser(empNo);
            saWxMessage.setAgentId(Constants.WX_AGENT_ID);
            saWxMessage.setMsgType("text");
            saWxMessage.setContent("您今日尚未添加拜访计划，如无拜访事项，请添加非拜访事项！！");
            saWxMessage.setCreatedBy(Constants.DEFAULT_USER_ID);
            Date date=new Date();
            saWxMessage.setCreatedTime(date);
            saWxMessage.setUpdatedBy(Constants.DEFAULT_USER_ID);
            saWxMessage.setUpdatedTime(date);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 10);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            Date m6= c.getTime();
            saWxMessage.setVersion(0);
            saWxMessage.setSendTime(m6);
            saWxMessage.setSaWxMessageId(UuidUtils.compressedUuid());

            saWxMessageMapper.save(saWxMessage);
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, empId, method, e.getMessage(), null);
            result.put("status", "fail");
        }
        return result;
    }



}
