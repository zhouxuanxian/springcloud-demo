package com.guanke.vinda.samodels.model.entity;

import org.apache.commons.lang.StringUtils;
import com.google.common.base.Splitter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class WxCpMessageSendResultEntity implements Serializable {

    private Integer errCode;


    private String errMsg;


    private String invalidUser;


    private String invalidParty;


    private String invalidTag;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getInvalidUser() {
        return invalidUser;
    }

    public void setInvalidUser(String invalidUser) {
        this.invalidUser = invalidUser;
    }

    public String getInvalidParty() {
        return invalidParty;
    }

    public void setInvalidParty(String invalidParty) {
        this.invalidParty = invalidParty;
    }

    public String getInvalidTag() {
        return invalidTag;
    }

    public void setInvalidTag(String invalidTag) {
        this.invalidTag = invalidTag;
    }

    public List<String> getInvalidUserList() {
        return this.content2List(this.invalidUser);
    }

    private List<String> content2List(String content) {
        if (StringUtils.isBlank(content)) {
            return Collections.emptyList();
        }

        return Splitter.on("|").splitToList(content);
    }


    @Override
    public String toString() {
        return "WxMessageSendResult{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                ", invalidUser='" + invalidUser + '\'' +
                ", invalidParty='" + invalidParty + '\'' +
                ", invalidTag='" + invalidTag + '\'' +
                '}';
    }

    public List<String> getInvalidPartyList() {
        return this.content2List(this.invalidParty);
    }

    public List<String> getInvalidTagList() {
        return this.content2List(this.invalidTag);
    }
}
