package com.wezaam.withdrawal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Enable swagger config
 * URL: http://localhost:8091/swagger-ui/
 * @author dvicensnoguera
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			        .select()
			        .apis(RequestHandlerSelectors.basePackage("com.wezaam.withdrawal.controller"))
			        .paths(PathSelectors.any())
			        .build();
	}

}
