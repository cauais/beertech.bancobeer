package com.beertech.banco.infrastructure.rest.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beertech.banco.infrastructure.repository.mysql.model.MySqlConta;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
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
          .apis(RequestHandlerSelectors.basePackage("com.beertech.banco.infrastructure"))
          .paths(PathSelectors.any())
          .build()
          .ignoredParameterTypes(MySqlConta.class)
          .globalOperationParameters(
                  Arrays.asList(
                          new ParameterBuilder()
                              .name("Authorization")
                              .description("Header para Token JWT")
                              .modelRef(new ModelRef("string"))
                              .parameterType("header")
                              .required(false)
                              .build()));
    }
}
