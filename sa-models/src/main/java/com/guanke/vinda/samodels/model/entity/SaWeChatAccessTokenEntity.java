package com.guanke.vinda.samodels.model.entity;

import com.guanke.vinda.samodels.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信AccessToken存储表，一个AccessToken时效为7200秒
 *
 * @author Nicemorning
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaWeChatAccessTokenEntity extends BaseEntity {
    private String accessToken;
    private Integer expires;
}
