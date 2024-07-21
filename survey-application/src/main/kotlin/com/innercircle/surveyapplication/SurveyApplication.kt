package com.innercircle.surveyapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@SpringBootApplication()
@Import(com.innercircle.surveyapi.api.SurveyApiControllerImpl::class)
@ComponentScan(basePackages = ["com.innercircle.surveyapi", "com.innercircle.surveyapplication"])
class SurveyApplication

fun main(args: Array<String>) {
    runApplication<SurveyApplication>(*args)
}


