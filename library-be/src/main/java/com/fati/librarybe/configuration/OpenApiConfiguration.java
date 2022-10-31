package com.fati.librarybe.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 26/10/2022
 * @author fati
 * @version 1.0
 */

@Configuration
public class OpenApiConfiguration {
    public static final String BOOK_TAG = "Books";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Library Backend API")
                        .version("1.0.0")
                        .description("Managing library related operations.")
                        .termsOfService("https://fati.com/terms")
                        .license(new License().name("Apache 2.0").url("https://fati.com")));
    }
}
