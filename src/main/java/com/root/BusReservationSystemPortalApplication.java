package com.root;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@OpenAPIDefinition(
//		info = @Info(
//				title = "Spring boot REST API Documentation for Bus Reservation System",
//				description = "Demo Bus Reservation System",
//				version = "v1.0",
//				contact = @Contact(
//						name = "Md Afzal",
//						email = "mdafzal9632@gmail.com"
//				)
//
//
//
//		)
//)
//@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1"),
//		security = {
//				@SecurityRequirement(name = "basicAuth"),
//				@SecurityRequirement(name = "bearerToken")
//		},
//		servers = {
//				@Server(url = "/", description = "Default Server URL")
//		}
//)
//@SecuritySchemes({
//		@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
//		@SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
//})
@SpringBootApplication
public class BusReservationSystemPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusReservationSystemPortalApplication.class, args);
	}

}