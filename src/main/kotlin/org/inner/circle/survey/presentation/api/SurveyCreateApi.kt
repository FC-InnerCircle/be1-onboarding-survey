package org.inner.circle.survey.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.inner.circle.survey.application.service.SurveyCreateService
import org.inner.circle.survey.presentation.dto.SurveyCreateRequest.CreateSurvey
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Survey", description = "설문지 생성 API")
@RestController
@RequestMapping("/api/survey")
class SurveyCreateApi(
    private val surveyCreateService: SurveyCreateService,
) {

    @Operation(summary = "설문지 생성", description = "설문지 생성 API")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "설문지 생성에 대한 응답",
        )
    )
    @PostMapping
    fun createSurvey(
        @Valid
        @RequestBody
        createSurvey: CreateSurvey,
    ): ResponseEntity<Void> {
        surveyCreateService.saveSurvey(createSurvey)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
