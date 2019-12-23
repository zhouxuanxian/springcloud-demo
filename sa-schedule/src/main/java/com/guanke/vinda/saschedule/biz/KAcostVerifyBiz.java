package com.guanke.vinda.saschedule.biz;

import java.util.List;
import java.util.Map;

public interface KAcostVerifyBiz {


    void needKASendCheck();

    List<Map<String, Object>> queryNeedSendMsg(Map<String, Object> qp, String model, String method);

    }
