package com.eduromero.accounts;

import com.eduromero.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(AccountsContactInfoDto.class)
@OpenAPIDefinition(
		info=@Info(
				title="Accounts microservice REST API documentation",
				description="Evil Ass Banking Accounts microservice REST API documentation",
				version="1.5",
				contact=@Contact(
						name="Edu Romero",
						email="edu@romero.com",
						url="http://evilassbanking.fu"
				),
				license=@License(
						name="Apache 2.0",
						url="http://evilassbanking.fu"
				)
		),
		externalDocs=@ExternalDocumentation(
				description="Evil Ass Banking Accounts microservice REST API documentation",
				url="http://evilassbanking.fu/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
