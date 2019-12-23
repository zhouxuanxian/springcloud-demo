package com.guanke.vinda.safunc.biz;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 销量提报相关业务逻辑
 *
 * @author ldm
 */
public interface SaleReportBiz {
    /**
     * 获取销量提报列表
     *
     * @param date       日期
     * @param status     提报状态
     * @param keyWord    关键字
     * @param positionId 职位id
     * @param empId      人员id
     * @param pageNum    页码
     * @param pageSize   页大小
     * @return 销量提报列表
     */
    Page<Map<String, Object>> getSaleReportLists(String date, String status, String keyWord, String positionId, String empId, int pageNum, int pageSize);

    /**
     * 获取销量提报报表
     *
     * @param shopIntId 店铺intid
     * @return 销量提报 分析报表
     */
    List<Map<String, Object>> getAccountSalesReportCounts(String shopIntId);
    /**
     * 获取门店名称
     *
     * @param shopIntId 店铺intid
     * @return 销量提报 分析报表
     */
    Map<String, Object> getShopName(String shopIntId);

    /**
     * 获取销量提报报表分析详情
     *
     * @param shopIntId 店铺intid
     * @param date 年月
     * @return 销量提报 分析报表
     */
    Map<String, Object> getAccountSalesReportCountDetail(String shopIntId,String date);

    /**
     * 获取品牌列表
     *
     * @param positionId 职位id
     * @param empId      人员id
     * @param date       日期
     * @param shopId     店铺id
     * @param salesReportId 销量提报id
     * @return 提报品牌列表
     */
    Map<String, Object> getSaleReportBrands(String positionId, String empId, String date, String shopId, String salesReportId);


}
