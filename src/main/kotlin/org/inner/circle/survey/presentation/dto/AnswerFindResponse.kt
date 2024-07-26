package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.inner.circle.survey.domain.enumeration.QuestionType

class AnswerFindResponse {
    @Schema(description = "답변 조회 응답")
    data class AnswerResponse(
        @field:Schema(description = "설문지 ID", required = true, type = "long")
        val surveyId: Long,
        @field:Schema(description = "설문지 제목", required = true, type = "string")
        val surveyTitle: String,
        @field:Schema(description = "설문지 설명", required = false, type = "string")
        val description: String? = "",
        @field:Schema(description = "작성자", required = true, type = "string")
        val writer: String,
        @field:Schema(description = "답변 내용", required = true)
        val questions: List<QuestionResponse>,
    )

    @Schema(description = "질문 조회 응답")
    data class QuestionResponse(
        @field:Schema(description = "질문 ID", required = true, type = "long")
        val questionId: Long,
        @field:Schema(description = "질문 제목", required = true, type = "string")
        val title: String,
        @field:Schema(description = "질문 유형", required = true, type = "string")
        val type: QuestionType,
        @field:Schema(description = "질문 설명", required = false, type = "string")
        val description: String? = "",
        @field:Schema(description = "필수 여부", required = true, type = "boolean")
        val content: String,
        @field:Schema(description = "순서", required = true, type = "int")
        val orderNumber: Int,
    )
}
