package com.guanke.vinda.sasync.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author:ldm
 * date:14:14 2019/11/27
 * describe: 默认描述
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 扫包的范围,也就是把哪些包生成api文档
                .apis(RequestHandlerSelectors.basePackage("com.guanke.vinda.sasync.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        //title文档标题
        //description文档描述
        //termsOfServiceUrl自定义地址
        //version版本号
        return new ApiInfoBuilder().title("经销宝系统sync").description("微服务swagger sa-sync")
                .termsOfServiceUrl("http://www.winkeytech.com/")
                // .contact(contact)
                .version("1.0").build();
    }

}
