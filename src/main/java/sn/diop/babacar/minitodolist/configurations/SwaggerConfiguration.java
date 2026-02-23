package sn.diop.babacar.minitodolist.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    /**
     * Configure le bean OpenAPI pour la documentation Swagger.
     *
     * @return la configuration OpenAPI avec le titre et la version
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini Todo List API")
                        .version("v1.0.0"));
    }
}