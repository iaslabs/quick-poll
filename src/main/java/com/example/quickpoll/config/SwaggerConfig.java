package com.example.quickpoll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT =
            new Contact("Test Example","testexample.com","example@test.com");
    public static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo("Name Api", "Example description Api", "1.0",
                    "Costumize",DEFAULT_CONTACT,"MIT License",
                    "http://opensource.org/licenses/MIT", new ArrayList<>());
    private static final Set<String> DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES =
            new HashSet<>(Collections.singletonList("application/json"));


    @Bean
    public Docket docketApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES_AND_CONSUMES)
                .useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.basePackage("com.example.quickpoll.controller"))
                .build();
    }




}
