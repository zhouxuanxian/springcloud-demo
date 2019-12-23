package com.guanke.vinda.safunc.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.guanke.vinda.safunc.biz.ShowApiBiz;
import com.guanke.vinda.safunc.config.ShowApiConfig;
import com.guanke.vinda.safunc.mapper.GkCompetitorMapper;
import com.guanke.vinda.samodels.model.entity.GkCompetitorEntity;
import com.guanke.vinda.samodels.model.pojo.show.api.BarCodePojo;
import com.guanke.vinda.samodels.model.pojo.show.api.ShowapiResBody;
import com.show.api.ShowApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 易源数据业务接口
 *
 * @author Nicemorning
 */
@Service
public class ShowApiBizImpl implements ShowApiBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowApiBizImpl.class);

    private final ShowApiConfig showApiConfig;

    private final GkCompetitorMapper gkCompetitorMapper;

    public ShowApiBizImpl(ShowApiConfig showApiConfig,
                          GkCompetitorMapper gkCompetitorMapper) {
        this.showApiConfig = showApiConfig;
        this.gkCompetitorMapper = gkCompetitorMapper;
    }

    /**
     * 通过条形码的值获取该商品的信息
     *
     * @param code 条形码的值
     * @return 该条形码的商品信息
     */
    @Override
    public BarCodePojo scanBarCode(String code) {
        String barCode = code;
        // 微工作台进入的用户扫码会在前面追加EAN_13,
        if (code.contains(",")) {
            barCode = code.split(",")[1];
        }
        String res = new ShowApiRequest("http://route.showapi.com/66-22",
                showApiConfig.getAppId(),
                showApiConfig.getSecret())
                .addTextPara("code", barCode)
                .post();
        BarCodePojo barCodePojo = JSONObject.toJavaObject(JSONObject.parseObject(res), BarCodePojo.class);
        ShowapiResBody showapiResBody = barCodePojo.getShowapiResBody();
        // 查询该竞品是否已存在数据库中
        GkCompetitorEntity gkCompetitorEntity = gkCompetitorMapper.selectFirstByBarCodeNumber(barCode);
        if (gkCompetitorEntity == null) {
            // 如果不存在则新建
            gkCompetitorEntity = new GkCompetitorEntity();
            gkCompetitorEntity.setBarCodeNumber(barCode);
            gkCompetitorEntity.setBrand(showapiResBody.getTrademark());
            gkCompetitorEntity.setKeywords(showapiResBody.getImg());
            gkCompetitorEntity.setPicUrl(gkCompetitorEntity.getKeywords());
            gkCompetitorEntity.setPrice(showapiResBody.getPrice());
            gkCompetitorEntity.setProductName(showapiResBody.getGoodsName());
            gkCompetitorEntity.setSpecification(showapiResBody.getSpec());
            if (gkCompetitorMapper.insertSelective(gkCompetitorEntity) <= 0) {
                LOGGER.info("Insert new competitor info failed");
            }
        }
        barCodePojo.setId(gkCompetitorEntity.getId());
        return barCodePojo;
    }
}
