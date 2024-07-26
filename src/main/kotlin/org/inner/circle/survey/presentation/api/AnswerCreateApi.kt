package org.inner.circle.survey.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.inner.circle.survey.application.service.AnswerCreateService
import org.inner.circle.survey.presentation.dto.AnswerCreateRequest.CreateAnswer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Answer", description = "설문지 답변제출 API")
@RestController
@RequestMapping("/api/answer")
class AnswerCreateApi(
    private val answerCreateService: AnswerCreateService,
) {
    @Operation(summary = "설문지 답변 제출", description = "설문지 답변 제출 API")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "설문지 답변 제출에 대한 응답",
        )
    )
    @PostMapping
    fun createSurveyAnswer(
        @Valid
        @RequestBody
        createAnswer: CreateAnswer,
    ): ResponseEntity<Void> {
        answerCreateService.saveAnswer(createAnswer)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
