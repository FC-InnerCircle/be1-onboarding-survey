@file:Suppress("SpringComponentScan")

package com.innercircle.surveyapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@Import(
//    com.innercircle.surveydomain.service.SurveyServiceImpl::class,
    com.innercircle.surveyapi.api.SurveyApiControllerImpl::class,
)
@EntityScan(basePackages = ["com.innercircle.surveydomain.model"])
@EnableJpaRepositories(basePackages = ["com.innercircle.surveydomain.repository"])
@ComponentScan(
    basePackages = [
        "com.innercircle.surveyapi",
        "com.innercircle.surveyapplication",
        "com.innercircle.surveydomain",
        "com.innercircle.surveycommon",
    ],
)
class SurveyApplication

fun main(args: Array<String>) {
    runApplication<SurveyApplication>(*args)
}
