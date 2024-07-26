package com.innercircle.surveyapplication

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["com.innercircle.surveydomain.model"])
@EnableJpaRepositories(basePackages = ["com.innercircle.surveydomain.repository"])
class JpaConfig
