package dev.matheusvictor.blogsenna;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default server url")})
@SpringBootApplication
public class BlogSennaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSennaApplication.class, args);
	}

}
