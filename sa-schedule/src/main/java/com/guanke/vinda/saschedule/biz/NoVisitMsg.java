package com.guanke.vinda.saschedule.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NoVisitMsg {
    /**
     * 生成未拜访提醒消息
     */
    void noVisitMsg();

    /**
     * 查询今天未拜访 的拜访的所有员工（工作日）
     * @param date
     * @param model
     * @param method
     * @return
     */
    List<Map<String, Object>> getEmpNoListOfNoVisit(Date date, String model, String method);
}
