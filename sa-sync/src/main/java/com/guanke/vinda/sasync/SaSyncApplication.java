package com.guanke.vinda.sasync;

import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Log4j
@SpringBootApplication(scanBasePackages = "com.guanke.vinda.sasync.*")
@MapperScan("com.guanke.vinda.sasync.mapper")
@EnableEurekaClient
public class SaSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaSyncApplication.class, args);
    }

}
