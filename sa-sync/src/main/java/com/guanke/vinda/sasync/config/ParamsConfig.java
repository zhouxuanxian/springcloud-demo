package com.guanke.vinda.sasync.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "saconfig")
@Data
public class ParamsConfig {
    private String photoPrefix;
}
