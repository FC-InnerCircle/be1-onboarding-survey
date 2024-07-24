package org.inner.circle.survey.adapter.controller

import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.UpdateSurvey
import org.inner.circle.survey.application.SurveyUpdateUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/survey")
class SurveyUpdateController(
    private val surveyUpdateUseCase: SurveyUpdateUseCase,
) {
    @PatchMapping
    fun updateSurvey(
        @RequestBody updateSurvey: UpdateSurvey,
    ): ResponseEntity<Void> {
        surveyUpdateUseCase.updateSurvey(updateSurvey)
        return ResponseEntity.ok().build()
    }
}
