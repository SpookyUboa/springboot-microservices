package com.eduromero.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API Documentation",
                description = "Most Evil Enterprises Accounts microservice documentation",
                version = "v1",
                contact = @Contact(
                        name = "Edu Romero",
                        email = "eduardojrl927@gmail.com",
                        url = "http://github.com/SpookyUboa"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://github.com/SpookyUboa"
                )
        ),
		externalDocs = @ExternalDocumentation(
				description = "Most Evil Enterprises Accounts microservice documentation",
				url = "http://example.url"
		)
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
