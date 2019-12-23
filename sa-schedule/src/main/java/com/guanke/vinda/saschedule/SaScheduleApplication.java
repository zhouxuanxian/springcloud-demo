package com.guanke.vinda.saschedule;

import lombok.extern.log4j.Log4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log4j
@SpringBootApplication(scanBasePackages = "com.guanke.vinda.saschedule.*")

@EnableScheduling
@MapperScan("com.guanke.vinda.saschedule.mapper")
@EnableEurekaClient
public class SaScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaScheduleApplication.class, args);
    }

}
