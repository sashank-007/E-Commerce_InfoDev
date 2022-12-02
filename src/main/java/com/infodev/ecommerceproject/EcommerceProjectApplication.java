package com.infodev.ecommerceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.logging.Logger;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
//@EnableWebMvc
public class EcommerceProjectApplication {

	public static final Logger log = Logger.getLogger(EcommerceProjectApplication.class.getName());
	public static void main(String[] args) {
		log.info("<========== Starting E-commerce Application =============>");
		SpringApplication.run(EcommerceProjectApplication.class, args);
	}

	@Bean
	public Docket ecommerceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.infodev.ecommerceproject"))
//				.paths(PathSelectors.ant("/*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"E-commerce Project API Documentation",
				"Sample E-commerce project for InfoDevelopers Assessment",
				"1.0",
				"Learning purposes only",
				new Contact("Sashank Shakya","sashankshakya.com.np","sashank.shakya07@gmail.com"),
				"No License",
				"sashankshakya.com.np",
				Collections.emptyList()
		);
	}
}
