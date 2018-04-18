package com.zoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(
                        PathSelectors.ant("/rest/animals/**"),
                        PathSelectors.ant("/rest/staff/**"),
                        PathSelectors.ant("/csv/animals/**")))

                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder
                .title("zoo-project Api").version("1.0.5")
                .license("(MIT) Open Source")
                .description("The API documentation for \"zoo-project\" project.");
        return builder.build();
    }
}
