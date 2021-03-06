package com.cg;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2WebMvc
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class ApplicationSwaggerConfig {
		
	@Bean
	public Docket adminApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
		private ApiInfo getApiInfo() {
			return new ApiInfoBuilder()
				.title("NGO Portal API")
				.version("1.0")
				.description("API for NGO Portal")
				.contact(new Contact("Parth Lokhande", "https://parth-lokhande.com", "parthlokhande@gmail.com"))
				.license("Apache Lisence Version 2.0")
				.build();
		}
	}