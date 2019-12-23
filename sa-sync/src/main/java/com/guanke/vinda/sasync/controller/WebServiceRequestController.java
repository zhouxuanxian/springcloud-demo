package com.guanke.vinda.sasync.controller;

import com.guanke.vinda.samodels.model.response.ObjectGeneralResponseEntity;
import com.guanke.vinda.sasync.biz.ShopUpdateOrSaveBiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * WEB SERVICE请求通用接口
 *
 * @author Nicemorning
 */
@RestController
@RequestMapping("wsController")
public class WebServiceRequestController {
    private final ShopUpdateOrSaveBiz shopUpdateOrSaveBiz;

    public WebServiceRequestController(ShopUpdateOrSaveBiz shopUpdateOrSaveBiz) {
        this.shopUpdateOrSaveBiz = shopUpdateOrSaveBiz;
    }

    @PostMapping("request")
    public ObjectGeneralResponseEntity request(@RequestBody Map<String, Object> params) {
        return new ObjectGeneralResponseEntity.Builder()
                .data(shopUpdateOrSaveBiz.generaWebServiceRequest(params)).build();
    }
}
