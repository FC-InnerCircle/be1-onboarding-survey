package com.innercircle.surveyapi.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Component
@Tag(name = "Surveys", description = "Survey operations")
@RestController
@RequestMapping("/api/surveys")
class SurveyApiControllerImpl : SurveyApiController {
    override fun getAllSurveys(): List<String> {
        return listOf("Survey 1", "Survey 2", "Survey 3")
    }
}
