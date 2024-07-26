package org.inner.circle.survey.common.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiDocConfig {
    @Bean
    fun openApi(): OpenAPI = OpenAPI().info(apiInfo())

    private fun apiInfo(): Info =
        Info()
            .title("Survey API 문서")
            .description("API 문서 종류")
            .version("v1.0.0")
}
