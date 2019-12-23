package com.guanke.vinda.safunc.biz;

import com.guanke.vinda.samodels.model.pojo.show.api.BarCodePojo;

/**
 * 易源数据业务接口
 *
 * @author Nicemorning
 */
public interface ShowApiBiz {
    /**
     * 通过条形码的值获取该商品的信息
     *
     * @param code 条形码的值
     * @return 该条形码的商品信息
     */
    BarCodePojo scanBarCode(String code);
}
