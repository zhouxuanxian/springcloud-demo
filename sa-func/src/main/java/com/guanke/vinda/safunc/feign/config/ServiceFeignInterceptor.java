package com.guanke.vinda.safunc.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Nicemorning
 */
@Component
public class ServiceFeignInterceptor implements RequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFeignInterceptor.class);

    public ServiceFeignInterceptor() {

    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        LOGGER.info("Feign interceptor======================");
    }
}
