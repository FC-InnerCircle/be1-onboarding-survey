package com.innercircle.surveyapi.config
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun surveyApiGroup(): GroupedOpenApi =
        GroupedOpenApi
            .builder()
            .group("surveys")
            .pathsToMatch("/v1/api/**")
            .build()

    @Bean
    fun customOpenAPI(): OpenAPI =
        OpenAPI()
            .components(Components())
            .info(
                Info().apply {
                    title("Inner Circle Survey API")
                    description("API for managing surveys")
                    version("1.0.0")
                    contact(
                        Contact().apply {
                            name("이너서클 백엔트파트 장현호")
                            email("hyunho.jang.dev@gmail.com")
                            url("https://github.com/hyunolike")
                        },
                    )
                },
            )
}
