package org.inner.circle.survey.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.inner.circle.survey.application.service.SurveyUpdateService
import org.inner.circle.survey.presentation.dto.SurveyUpdateRequest.UpdateSurvey
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Survey", description = "설문지 수정 API")
@RestController
@RequestMapping("/api/survey")
class SurveyUpdateApi(
    private val surveyUpdateService: SurveyUpdateService,
) {

    @Operation(summary = "설문지 수정", description = "설문지 수정 API")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "설문지 수정에 대한 응답",
        )
    )
    @PatchMapping
    fun updateSurvey(
        @Valid
        @RequestBody
        updateSurvey: UpdateSurvey,
    ): ResponseEntity<Void> {
        surveyUpdateService.updateSurvey(updateSurvey)
        return ResponseEntity.ok().build()
    }
}
