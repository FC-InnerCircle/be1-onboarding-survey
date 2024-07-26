package org.inner.circle.survey.presentation.api

import org.inner.circle.survey.application.service.SurveyFindService
import org.inner.circle.survey.presentation.dto.SurveyFindResponse.SurveyResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/survey")
class SurveyFindApi(
    private val surveyFindUseCase: SurveyFindService
) {

    @GetMapping("/{surveyId}")
    fun findSurvey(
        @PathVariable surveyId: Long
    ): ResponseEntity<SurveyResponse> {
        return ResponseEntity.ok(surveyFindUseCase.findSurvey(surveyId))
    }

}