package bernie.ver1.berniever1.config;

// import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.info.Info;

@Configuration
// @OpenAPIDefinition(info = @Info(title = "Bernie API Documentation", version = "1.0.0"))
public class SwaggerConfig implements WebMvcConfigurer {

    // @Bean
    // public GroupedOpenApi publicApi() {
    //     return GroupedOpenApi.builder()
    //             .group("public")
    //             .packagesToScan("bernie.ver1.berniever1.controller") // Thay thế bằng gói chứa các controller của bạn
    //             .build();
    // }


}

