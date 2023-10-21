package org.serratec.TrabalhoFinalApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.serratec.TrabalhoFinalApi"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());

	}
	
	private ApiInfo apiInfo() {
		
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API de rede social")
				.description("Uma API para criar usuários, seguir usuários, postar e comentar em posts.")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/vitorhbf/TrabalhoFinalApi/")
				.version("1.0.0")
				.contact(new Contact("Serratec", "https://www.serratec.org/", "trabalho_api@gmail.com" ))
				.build();
				
				return apiInfo;
				
	}
}
