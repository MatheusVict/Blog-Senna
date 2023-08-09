package dev.matheusvictor.blogsenna.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
  }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement().
                    addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes
                    ("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info().title("My REST API")
                    .description("API from the Senna Blog to talk about automobilism")
                    .version("1.0").contact(new Contact().name("Matheus Victor")
                            .email( "matheusvictorhenrique@gmail.com").url("https://portfolio-kappa-ten-20.vercel.app/"))
                    .license(new License().name("License of API")
                            .url("API license URL")));
  }

}
