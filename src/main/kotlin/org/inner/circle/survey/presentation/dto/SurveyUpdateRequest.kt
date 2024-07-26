package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import org.inner.circle.survey.domain.enumeration.QuestionType

class SurveyUpdateRequest {

    @Schema(description = "설문지 수정 요청")
    data class UpdateSurvey(
        @field:Schema(description = "설문지 ID", required = true, type = "long")
        @NotNull(message = "설문지 ID를 입력해주세요.")
        val id: Long,
        @field:Schema(description = "제목", required = true, type = "string")
        val title: String?,
        @field:Schema(description = "설명", required = false, type = "string")
        val description: String?,
        @field:Schema(description = "시작일", required = true, type = "string")
        val questions: List<Questions>?,
    )

    @Schema(description = "질문 정보")
    data class Questions(
        @field:Schema(description = "질문 ID", required = true, type = "long")
        @NotNull(message = "질문 ID를 입력해주세요.")
        val id: Long,
        @field:Schema(description = "제목", required = true, type = "string")
        val title: String?,
        @field:Schema(description = "질문 유형", required = true, type = "string")
        val type: QuestionType?,
        @field:Schema(description = "설명", required = false, type = "string")
        val description: String?,
        @field:Schema(description = "선택지", required = false)
        val options: List<Options>?,
        @field:Schema(description = "필수 여부", required = false)
        val isRequired: Boolean?,
        @field:Schema(description = "순서", required = false)
        val orderNumber: Int?,
    )

    @Schema(description = "선택지 정보")
    data class Options(
        @field:Schema(description = "선택지 ID", required = true, type = "long")
        @NotNull(message = "선택지 ID를 입력해주세요.")
        val id: Long,
        @field:Schema(description = "내용", required = false, type = "string")
        val content: String?,
        @field:Schema(description = "순서", required = false)
        val orderNumber: Int?,
    )
}
