package br.com.jumpcat.agicad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
				.title("API RESTful AGICAD - Cadastro Agil, construída com Spring Boot 2.5.0")
				.version("V1")
				.description("Sistema de agendamento de horario de clientes com prestadores de servicos")
				.termsOfService("http://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));		
	}
}

