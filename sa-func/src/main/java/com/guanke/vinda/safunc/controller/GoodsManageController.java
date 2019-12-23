package com.guanke.vinda.safunc.controller;

import com.guanke.vinda.safunc.biz.ShowApiBiz;
import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品类接口
 *
 * @author Nicemorning
 */
@RequestMapping("goods")
@RestController
public class GoodsManageController {
    private final ShowApiBiz showApiBiz;

    public GoodsManageController(ShowApiBiz showApiBiz) {
        this.showApiBiz = showApiBiz;
    }

    @ApiOperation("扫码获取商品信息")
    @GetMapping("scanForGoodsInfo")
    public ObjectGeneralResponseEntity scanForGoodsInfo(@RequestParam("code") @ApiParam("商品编号") String code) {
        return new ObjectGeneralResponseEntity.Builder().data(
                showApiBiz.scanBarCode(code)
        ).build();
    }

}
