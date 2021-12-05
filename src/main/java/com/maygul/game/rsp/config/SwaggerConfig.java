package com.maygul.game.rsp.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.maygul.game.rsp.api")).build().apiInfo(metaData())
            .select()
            .paths(PathSelectors.any())
            .build()
            .enableUrlTemplating(true);
    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
            "Spring Boot REST Api for Clinic",
            "study case",
            "1.0",
            "Terms of Service",
            new Contact(
                "Mustafa AYGÜL",
                "https://discord.io/SupremeSignal",
                "social.maygul@gmail.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE,-2.0",
            new ArrayList<>()
        );
        return apiInfo;
    }
}
