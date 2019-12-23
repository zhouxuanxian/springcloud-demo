package com.guanke.vinda.sasync.biz;

import java.util.Map;

/**
 * 销量提报业务类
 *
 * @author Nicemorning
 */
public interface SaleReportSaveBiz {

    /**
     * 销量提报 提交
     *
     * @param empId      用户id
     * @param positionId 职位id
     * @param params     提报信息
     * @return 发送成功
     */
    boolean saveSaleReport(String empId, String positionId,String shopId,String intId,String date, Map<String, Object> params);

}
