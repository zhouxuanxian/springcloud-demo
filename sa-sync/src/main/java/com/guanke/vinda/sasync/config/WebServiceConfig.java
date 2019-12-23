package com.guanke.vinda.sasync.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebService配置文件
 *
 * @author Nicemorning
 */
@Component
@ConfigurationProperties(prefix = "ws")
@Data
public class WebServiceConfig {
    private String tagPrefix;
    private String bodyPrefix;
    private String bodyUri;
    private String headerPrefix;
    private String headerUri;
    private String username;
    private String password;
}
