package com.guanke.vinda.saschedule.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DailyMsgBiz {
    /**
     *
     */

    void dailyMsg();

    /**
     * 查询没有提交日报的员工列表
     * @param date
     * @param model
     * @param method
     * @return
     */
    List<Map<String, Object>> getEmpNoListOfNoSubmitDaily(Date date, String model, String method);
}
