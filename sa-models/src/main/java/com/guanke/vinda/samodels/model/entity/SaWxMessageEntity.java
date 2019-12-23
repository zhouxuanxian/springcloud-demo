package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

public class SaWxMessageEntity  implements Serializable {
    public SaWxMessageEntity(){
        this.status =0;

    }

    public SaWxMessageEntity(String recordId,String type){
        this.recordId = recordId;

        this.status = 0;

    }
    private String saWxMessageId; //
    private String createdBy;    //创建用户
    private Date createdTime;   //创建时间

    private String  updatedBy;  //最后修改用户
    private Date updatedTime;   //最后修改时间
    private  Integer version;  //版本号

    private String  tableName;  //表名
    private String recordId;	//消息来源id


    private String toUser;	//微信成员id
    private String toParty;	//微信部门id
    private String toTag;		//微信标签id
    private String msgType;		//微信消息类型 text
    private String content;	//微信消息内容 最长不超过2048个字节
    private Integer agentId;    //应用id


   private Integer status;		//发送状态 0 1 2
    private Date sendTime;		//发送时间

    private String wxResult;	//错误原因

    private String  title;   //文本卡片信息->标题
    private String  description;  //文本卡片信息->描述
    private String  url;    //文本卡片信息->跳转Url
    private String  btnTxt;  //文本卡片信息->按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
    private  String  mediaId;  //图片消息->图片媒体文件Id，可以调用上传临时素材或者永久素材接口获取,永久素材media_Id必须由发消息的应用创建

    public String getSaWxMessageId() {
        return saWxMessageId;
    }

    public void setSaWxMessageId(String saWxMessageId) {
        this.saWxMessageId = saWxMessageId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getWxResult() {
        return wxResult;
    }

    public void setWxResult(String wxResult) {
        this.wxResult = wxResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
