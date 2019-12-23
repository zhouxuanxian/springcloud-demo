package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.GkStockOrderBiz;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单/库存相关接口
 */
@RequestMapping("gkStockOrder")
@RestController
public class GkStockOrderController {
    private final GkStockOrderBiz gkStockOrderBiz;

    public GkStockOrderController(GkStockOrderBiz gkStockOrderBiz) {
        this.gkStockOrderBiz = gkStockOrderBiz;
    }

    @ApiOperation("根据条件分页查询订单/库存")
    @PostMapping("getGkStockByCondition")
    public PageableTableGeneralResponseEntity getGkStockByCondition(@RequestParam(value = "accountId", required = false) @ApiParam("门店Id") String accountId,
                                                                    @RequestParam(value = "empId", required = false) @ApiParam("员工Id") String empId,
                                                                    @RequestParam(value = "positionId", required = false) @ApiParam("职位Id") String positionId,
                                                                    @RequestParam(value = "visitId", required = false) @ApiParam("拜访Id") String visitId,
                                                                    @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页数量") Integer pageSize) {
        return new PageableTableGeneralResponseEntity.Builder().data(gkStockOrderBiz.getGkStockByCondition(accountId, empId, positionId, visitId, pageNum, pageSize)).build();
    }
}