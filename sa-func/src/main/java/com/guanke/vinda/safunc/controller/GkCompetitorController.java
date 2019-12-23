package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.GkCompetitorBiz;
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
@RequestMapping("gkCompetitor")
@RestController
public class GkCompetitorController {
    private final GkCompetitorBiz gkCompetitorBiz;

    public GkCompetitorController(GkCompetitorBiz gkCompetitorBiz) {
        this.gkCompetitorBiz = gkCompetitorBiz;
    }

    @ApiOperation("根据条件分页查询竞品")
    @PostMapping("getGkCompetitorByCondition")
    public PageableTableGeneralResponseEntity getGkCompetitorByCondition(@RequestParam(value = "accountId", required = false) @ApiParam("门店Id") String accountId,
                                                                         @RequestParam(value = "empId", required = false) @ApiParam("员工Id") String empId,
                                                                         @RequestParam(value = "positionId", required = false) @ApiParam("职位Id") String positionId,
                                                                         @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                                                         @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页数量") Integer pageSize) {
        return new PageableTableGeneralResponseEntity.Builder().data(gkCompetitorBiz.getGkCompetitorByCondition(accountId, empId, positionId, pageNum, pageSize)).build();
    }
}