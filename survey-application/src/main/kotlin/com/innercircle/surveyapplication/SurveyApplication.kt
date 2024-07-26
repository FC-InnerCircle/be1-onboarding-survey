@file:Suppress("SpringComponentScan")

package com.innercircle.surveyapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.*

@SpringBootApplication(scanBasePackages = ["com.innercircle"])
@EnableAspectJAutoProxy
@Import(
//    com.innercircle.surveyapi.common.RequestLoggingAspect::class,
    com.innercircle.surveyapi.api.SurveyApiControllerImpl::class,
)
@ComponentScan(
    basePackages = [
        "com.innercircle.surveyapi",
        "com.innercircle.surveyapplication",
        "com.innercircle.surveydomain",
        "com.innercircle.surveycommon",
        "com.innercircle.surveydomain.repository",
        "com.innercircle.surveydomain.service",
    ],
)
class SurveyApplication

fun main(args: Array<String>) {
    runApplication<SurveyApplication>(*args)
}
