package org.inner.circle.survey.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.inner.circle.survey.application.service.AnswerFindService
import org.inner.circle.survey.presentation.dto.AnswerFindRequest.FindAnswer
import org.inner.circle.survey.presentation.dto.AnswerFindResponse.AnswerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Answer", description = "설문지 답변 조회 API")
@RestController
@RequestMapping("/api/answer")
class AnswerFindApi(
    private val answerFindService: AnswerFindService,
) {

    @Operation(summary = "설문지 답변 조회", description = "설문지 답변 조회 API")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "설문지 답변 조회에 대한 응답",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AnswerResponse::class)
                )
            ]
        )
    )
    @GetMapping
    fun getSurveyInfo(
        @Valid
        findAnswer: FindAnswer
    ): ResponseEntity<AnswerResponse> {
        val answerResponse = answerFindService.findAnswer(findAnswer)
        return ResponseEntity.ok(answerResponse)
    }
}
