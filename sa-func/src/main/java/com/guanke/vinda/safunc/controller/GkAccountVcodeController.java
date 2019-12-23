package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.GkAccountVcodeBiz;
import com.guanke.vinda.samodels.model.response.PageableTableGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店V码相关接口
 */
@RequestMapping("gkAccountVcode")
@RestController
public class GkAccountVcodeController {
    private final GkAccountVcodeBiz gkAccountVcodeBiz;

    public GkAccountVcodeController(GkAccountVcodeBiz gkAccountVcodeBiz) {
        this.gkAccountVcodeBiz = gkAccountVcodeBiz;
    }

    @ApiOperation("根据条件分页查询门店V码")
    @PostMapping("getGkAccountVcodeByCondition")
    public PageableTableGeneralResponseEntity getGkAccountVcodeByCondition(@RequestParam(value = "accountId", required = false) @ApiParam("门店Id") String accountId,
                                                                           @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam("每页数量") Integer pageSize) {
        return new PageableTableGeneralResponseEntity.Builder().data(gkAccountVcodeBiz.getGkAccountVcodeByCondition(accountId, pageNum, pageSize)).build();
    }
}