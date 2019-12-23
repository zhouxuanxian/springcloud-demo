package com.guanke.vinda.safunc.controller;

import com.github.pagehelper.Page;
import com.guanke.vinda.safunc.biz.SaleReportBiz;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 销量提报相关接口
 *
 * @author ldm
 */
@Api("销量提报查询类")
@RestController
@RequestMapping("saleReport")
public class SaleReportController {

    private final SaleReportBiz saleReportBiz;

    public SaleReportController(SaleReportBiz saleReportBiz) {
        this.saleReportBiz = saleReportBiz;
    }


    @ApiOperation("获取销量提报列表")
    @PostMapping("getSaleReportList")
    public PageableTableGeneralResponseEntity getSaleReportList(@RequestParam @ApiParam("职位id") String positionId,
                                                                @RequestParam @ApiParam("人员Id") String empId,
                                                                @RequestParam @ApiParam("日期YYYY-MM") String date,
                                                                @RequestParam(required = false) @ApiParam("筛选状态") String status,
                                                                @RequestParam(required = false) @ApiParam(value = "搜索关键字", required = false) String keyword,
                                                                @RequestParam @ApiParam("页码") int pageNum,
                                                                @RequestParam @ApiParam("页大小") int pageSize
    ) {
        Page<Map<String, Object>> result = saleReportBiz.getSaleReportLists(date, status, keyword, positionId, empId, pageNum, pageSize);

        return new PageableTableGeneralResponseEntity.Builder().data(result.getResult()).
                total(result.getTotal()).build();
    }

    /**
     * 获取销量提报分析报表
     *
     * @param shopIntId 门店intId
     * @return 销量提报分析列表
     */
    @ApiOperation("获取销量提报分析报表")
    @PostMapping("getAccountSalesReportCount")
    public ObjectGeneralResponseEntity getAccountSalesReportCount(@RequestParam @ApiParam("门店intId（CRM的row_id）") String shopIntId) {

        return new ObjectGeneralResponseEntity.Builder().data(saleReportBiz.getAccountSalesReportCounts(shopIntId)).build();
    }

    /**
     * 获取销量提报报表明细
     *
     * @param shopIntId 门店intId
     * @return 销量提报分析列表
     */
    @ApiOperation("获取销量提报报表分析详情")
    @PostMapping("AccountSalesReportCountDetail")
    public ObjectGeneralResponseEntity getAccountSalesReportCountDetail(@RequestParam @ApiParam("门店intId（CRM的row_id）") String shopIntId,
                                                                        @RequestParam @ApiParam("年月YYYY-MM") String date) {
        return new ObjectGeneralResponseEntity.Builder().data(saleReportBiz.getAccountSalesReportCountDetail(shopIntId,date)).build();
    }
/**
     * 获取销量提报报表明细
     *
     * @param shopIntId 门店intId
     * @return 销量提报分析列表
     */
    @ApiOperation("获取门店名称")
    @PostMapping("getShopName")
    public ObjectGeneralResponseEntity getShopName(@RequestParam("shopIntId") @ApiParam("门店intId（CRM的row_id）") String shopIntId) {
        return new ObjectGeneralResponseEntity.Builder().data(saleReportBiz.getShopName(shopIntId)).build();
    }


    @ApiOperation("获取销量提报核销品牌详情")
    @PostMapping("/getSaleReportBrand")
    public ObjectGeneralResponseEntity getSaleReportBrand(@RequestParam @ApiParam("职位id") String positionId,
                                                          @RequestParam @ApiParam("人员Id") String empId,
                                                          @RequestParam @ApiParam("日期YYYY-MM") String date,
                                                          @RequestParam @ApiParam("门店id") String shopId,
                                                          @RequestParam @ApiParam("提报id") String salesReportId) {

        return new ObjectGeneralResponseEntity.Builder().data(
                saleReportBiz.getSaleReportBrands(positionId, empId, date, shopId, salesReportId)
        ).build();
    }


}
