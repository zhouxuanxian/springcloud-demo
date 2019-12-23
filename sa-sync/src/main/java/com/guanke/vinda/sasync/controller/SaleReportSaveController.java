package com.guanke.vinda.sasync.controller;

import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.sasync.biz.SaleReportSaveBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 销量提报 提交
 *
 * @author ldm
 */
@Api("销量提报提交类")
@RestController
@RequestMapping("saleReportSave")
public class SaleReportSaveController {
    private final SaleReportSaveBiz saleReportSaveBiz;

    public SaleReportSaveController(SaleReportSaveBiz saleReportSaveBiz) {
        this.saleReportSaveBiz = saleReportSaveBiz;
    }

    @ApiOperation("销量提报提交或变更")
    @PostMapping("save")
    public ObjectGeneralResponseEntity saveSaleReport(@RequestParam("positionId") @ApiParam("职位Id") String positionId,
                                                      @RequestParam("empId") @ApiParam("人员Id") String empId,
                                                      @RequestParam("shopId") @ApiParam("门店id") String shopId,
                                                      @RequestParam("intId") @ApiParam("门店intid(CRM对应的rowID)") String intId,
                                                      @RequestParam("date") @ApiParam("提报日期YYYY-MM") String date,
                                                      @RequestBody @ApiParam("品牌列表key为brandList") Map<String, Object> params) {

        if (saleReportSaveBiz.saveSaleReport(empId, positionId, shopId, intId, date, params)) {
            return new ObjectGeneralResponseEntity.Builder().msg("保存成功").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
    }
}
