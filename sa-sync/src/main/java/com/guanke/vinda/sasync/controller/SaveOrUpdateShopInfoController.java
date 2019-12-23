package com.guanke.vinda.sasync.controller;

import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.sasync.biz.ShopUpdateOrSaveBiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 新建门店或修改门店信息
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("shopInfoManage")
public class SaveOrUpdateShopInfoController {
    private final ShopUpdateOrSaveBiz shopUpdateOrSaveBiz;

    public SaveOrUpdateShopInfoController(ShopUpdateOrSaveBiz shopUpdateOrSaveBiz) {
        this.shopUpdateOrSaveBiz = shopUpdateOrSaveBiz;
    }

    @PostMapping("updateShopInfo")
    public ObjectGeneralResponseEntity updateShopInfo(@RequestBody Map<String, Object> params) {
        if (shopUpdateOrSaveBiz.updateShopInfo(params)) {
            return new ObjectGeneralResponseEntity.Builder().msg("更新成功").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
    }

    @PostMapping("saveShopInfo")
    public ObjectGeneralResponseEntity saveShopInfo(@RequestBody Map<String, Object> params) {
        if (shopUpdateOrSaveBiz.saveShopInfo(params)) {
            return new ObjectGeneralResponseEntity.Builder().msg("新建成功").build();
        } else {
            return new ObjectGeneralResponseEntity.Builder().code(500).build();
        }
    }
}
