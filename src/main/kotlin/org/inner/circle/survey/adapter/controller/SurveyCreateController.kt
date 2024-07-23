package org.inner.circle.survey.adapter.controller

import org.inner.circle.survey.adapter.dto.SurveyRequest.CreateSurvey
import org.inner.circle.survey.application.SurveyCreateUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/survey")
class SurveyCreateController(
    private val surveyCreateUseCase: SurveyCreateUseCase,
) {
    @PostMapping
    fun createSurvey(
        @RequestBody createSurvey: CreateSurvey,
    ): ResponseEntity<Void> {
        surveyCreateUseCase.saveSurvey(createSurvey)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
