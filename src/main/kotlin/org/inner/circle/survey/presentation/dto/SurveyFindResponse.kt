package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.enumeration.QuestionType

class SurveyFindResponse {

    @Schema(description = "설문지 조회 응답")
    data class SurveyResponse (
        @field:Schema(description = "설문지 ID", required = true, type = "long")
        val id: Long,
        @field:Schema(description = "설문지 이름", required = true, type = "string")
        val title: String,
        @field:Schema(description = "설문지 설명", required = false, type = "string")
        val description: String,
        @field:Schema(description = "질문 목록", required = true)
        val questions: List<QuestionResponse>
    )

    @Schema(description = "질문 조회 응답")
    data class QuestionResponse (
        @field:Schema(description = "질문 ID", required = true, type = "long")
        val id: Long,
        @field:Schema(description = "질문 제목", required = true, type = "string")
        val title: String,
        @field:Schema(description = "질문 유형", required = true, type = "string")
        val type: QuestionType,
        @field:Schema(description = "설명", required = false, type = "string")
        val description: String?="",
        @field:Schema(description = "선택지 목록", required = false)
        val options: List<OptionResponse>,
        @field:Schema(description = "필수 여부", required = true, type = "boolean")
        val isRequired: Boolean,
        @field:Schema(description = "순서", required = true, type = "int")
        val orderNumber: Int
    )

    @Schema(description = "선택지 조회 응답")
    data class OptionResponse (
        @field:Schema(description = "선택지 ID", required = true, type = "long")
        val id: Long,
        @field:Schema(description = "내용", required = true, type = "string")
        val content: String,
        @field:Schema(description = "순서", required = true, type = "int")
        val orderNumber: Int
    )
}