package com.simple.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.simple.test.utils.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Luis Serrato.
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(Constants.API_TITLE).description(Constants.API_DESCRIPTION)
				.version(Constants.API_CURRENT_VERSION)
				.contact(new Contact(Constants.CONTACT_NAME, "", Constants.CONTACT_EMAIL)).build();
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build().apiInfo(apiInfo());
	}

}
