package com.guanke.vinda.safunc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "show-api")
@Data
public class ShowApiConfig {
    private String appId;
    private String secret;
}
