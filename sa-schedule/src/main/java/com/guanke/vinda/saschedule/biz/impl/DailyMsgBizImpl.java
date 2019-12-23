package com.guanke.vinda.saschedule.biz.impl;

import com.guanke.vinda.saschedule.biz.DailyMsgBiz;
import com.guanke.vinda.saschedule.biz.DataRecordServiceBiz;
import com.guanke.vinda.saschedule.mapper.GkEmployeeMapper;
import com.guanke.vinda.saschedule.mapper.SaWxMessageMapper;
import com.guanke.vinda.saschedule.service.SyncLogService;
import com.guanke.vinda.saschedule.utils.DateUtil;
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
public class DailyMsgBizImpl implements DailyMsgBiz {

    final String MODEL = "DailyWxMessage";					//操作的对象
    final String METHOD = "生成日报提醒消息"; //方法

    @Resource
    GkEmployeeMapper gkEmployeeMapper;
    @Resource
    SaWxMessageMapper SaWxMessageMapper;
    @Resource
    SyncLogService syncLogService;
    @Resource
    DataRecordServiceBiz dataRecordService;

    @Transactional
    public void dailyMsg() {
        System.out.println(METHOD + "-开始："+DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss"));

        Map<String, Object> qp = new HashMap<String, Object>();

//        //1.删除三天以前的日报提醒数据
//        Date dayBeforeYday = DateUtil.addDays(new Date(), -3);
//        String dayBeforeYDayStr = DateUtil.convertDateToString(dayBeforeYday);
//        qp.put("dayBeforeYday", dayBeforeYDayStr);
//        dataRecordService.dailyMsgDel(qp, MODEL, METHOD);

        //2.查询今天未提交日报的所有员工
        String today = DateUtil.convertDateToString(new Date());

        List<Map<String, Object>> empList = this.getEmpNoListOfNoSubmitDaily(new Date(), MODEL, METHOD);

        int successSeq = 0;

        //3.生成日报提醒消息
        if(!CollectionUtils.isEmpty(empList)) {
            for(Map<String, Object> map : empList) {
                Map<String, Object> result = dataRecordService.createWxMessageByDailyPostn(map, MODEL, METHOD);
                if("success".equals((String)result.get("status"))){
                    successSeq++;
                }
            }
        }

        int totalSize = CollectionUtils.isEmpty(empList) ? 0 : empList.size();

        System.out.println(METHOD + "-结束："+ DateUtil.convertDateToString(new Date(),"yyyyMMddHHmmss")+ ",共：" + totalSize + "，成功：" + successSeq);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, Object>> getEmpNoListOfNoSubmitDaily(Date today, String model, String method) {
        try {
            List<Map<String, Object>> result =  gkEmployeeMapper.getEmpNoListOfNoSubmitDaily(today);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            syncLogService.saveError(model, null, method, e.getMessage(), null);
            return null;
        }
    }
}
