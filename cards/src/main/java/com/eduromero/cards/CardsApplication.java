package com.eduromero.cards;

import com.eduromero.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(CardsContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Evil Ass Banking Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Edu Romero",
						email = "edu@romero.com",
						url = "https://www.evilassbanking.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.evilassbanking.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Evil Ass Banking Cards microservice REST API Documentation",
				url = "https://www.Evil Ass Banking.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
