package com.kata.boardkata.configration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {

		Info info = new Info()
				.version("v1.0.0")
				.title("설문조사 API")
				.description("API Description");

		return new OpenAPI()
				.addServersItem(new Server().url("/"))
				.info(info);
	}

}