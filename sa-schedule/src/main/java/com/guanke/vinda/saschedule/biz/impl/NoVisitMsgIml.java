package com.guanke.vinda.saschedule.biz.impl;

import com.guanke.vinda.saschedule.biz.DataRecordServiceBiz;
import com.guanke.vinda.saschedule.biz.NoVisitMsg;
import com.guanke.vinda.saschedule.mapper.GkEmployeeMapper;
import com.guanke.vinda.saschedule.mapper.SaWxMessageMapper;
import com.guanke.vinda.saschedule.service.SyncLogService;
import com.guanke.vinda.saschedule.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class NoVisitMsgIml implements NoVisitMsg {
    final String MODEL = "NoVisitWxMessage";					//操作的对象
    final String METHOD = "生成在未拜访提醒消息（周一至周五）"; //方法

    @Resource
    GkEmployeeMapper gkEmployeeMapper;
    @Resource
    SaWxMessageMapper saWxMessageMapper;
    @Resource
    SyncLogService syncLogService;
    @Resource
    DataRecordServiceBiz dataRecordService;


    @Transactional
    public void noVisitMsg() {
        System.out.println(METHOD + "-开始："+ DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss"));

        Map<String, Object> qp = new HashMap<String, Object>();

//        //1.删除三天以前的拜访提醒数据
//        Date threeDaysAgo = DateUtil.addDays(new Date(), -3);
//        String threeDaysAgoStr = DateUtil.convertDateToString(threeDaysAgo);
//        qp.put("threeDaysAgo", threeDaysAgoStr);
//        dataRecordService.noVisitMsgDel(qp, MODEL, METHOD);
        String weekDay = DateUtil.getWeekDay(new Date());
        if(StringUtils.equals(weekDay, "星期日") || StringUtils.equals(weekDay, "星期六")) {
            return;
        }

        //2.查询今天未拜访 的拜访的所有员工（工作日）

        List<Map<String, Object>> empList = this.getEmpNoListOfNoVisit(new Date(), MODEL, METHOD);

        int successSeq = 0;

        //3.生成未拜访提醒消息
        if(!CollectionUtils.isEmpty(empList)) {
            for(Map<String, Object> map : empList) {
                Map<String, Object> result = dataRecordService.createWxMessageByNoVisitPostn(map, MODEL, METHOD);
                if("success".equals((String)result.get("status"))){
                    successSeq++;
                }
            }
        }

        int totalSize = CollectionUtils.isEmpty(empList) ? 0 : empList.size();

        System.out.println(METHOD + "-结束："+DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss")+ ",共：" + totalSize + "，成功：" + successSeq);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, Object>> getEmpNoListOfNoVisit(Date date, String model, String method) {
        try {


            List<Map<String, Object>> result =gkEmployeeMapper.getEmpNoListOfNoVisit(date);


            return result;
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, null, method, e.getMessage(), null);
            return null;
        }
    }
}
