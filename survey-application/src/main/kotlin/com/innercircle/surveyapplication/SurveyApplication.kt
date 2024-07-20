package com.innercircle.surveyapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SurveyApplication

fun main(args: Array<String>) {
    runApplication<SurveyApplication>(*args)
}
