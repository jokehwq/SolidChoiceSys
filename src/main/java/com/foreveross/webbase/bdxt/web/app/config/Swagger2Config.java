package com.foreveross.webbase.bdxt.web.app.config;

import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.foreveross.webbase.bdxt.web.app.controller" })
public class Swagger2Config {


    @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                ignoredParameterTypes(LoginUser.class).apiInfo(apiInfo()).forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("标的系统RESTful API").description("©2018 Copyright. Powered By biaodi.")
                .contact("标的系统")
                .version("1.0").build();
    }

}