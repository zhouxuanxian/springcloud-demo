package com.guanke.vinda.safunc;

import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nicemorning
 */
@Log4j
@SpringBootApplication(scanBasePackages = "com.guanke.vinda.safunc.*")
@MapperScan("com.guanke.vinda.safunc.mapper")
@EnableEurekaClient
@EnableFeignClients
public class SaFuncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaFuncApplication.class, args);
    }
}
