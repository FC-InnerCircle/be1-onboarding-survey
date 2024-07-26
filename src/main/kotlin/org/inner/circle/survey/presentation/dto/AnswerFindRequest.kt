package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class AnswerFindRequest {
    @Schema(description = "답변 조회 요청")
    data class FindAnswer(
        @field:Schema(description = "설문지 ID", required = true, type = "long")
        @NotNull(message = "설문지 ID는 필수입니다. ")
        val surveyId: Long,
        @field:Schema(description = "답변자", required = false, type = "string")
        val surveyTitle: String?,
        @field:Schema(description = "답변 내용", required = false)
        val answerContent: String?,
    )
}
