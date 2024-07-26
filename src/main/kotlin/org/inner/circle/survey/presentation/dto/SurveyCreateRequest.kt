package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.QuestionOption
import org.inner.circle.survey.domain.entity.Survey
import org.inner.circle.survey.domain.enumeration.QuestionType

class SurveyCreateRequest {

    @Schema(description = "설문지 생성 요청")
    data class CreateSurvey(
        @field:Schema(description = "제목", required = true, type = "string")
        @NotBlank(message = "제목을 입력해주세요.")
        val title: String,
        @field:Schema(description = "설명", required = false, type = "string")
        val description: String?,
        @field:Schema(description = "질문 목록", required = true)
        @NotBlank(message = "작성자를 입력해주세요.")
        val questions: List<Questions>,
        @field:Schema(description = "작성자", required = true, type = "string")
        @NotBlank(message = "작성자를 입력해주세요.")
        val writer: String,
    ) {
        fun toSurvey() =
            Survey(
                title = title,
                description = description ?: "",
                writer = writer,
            )
    }

    @Schema(description = "질문 생성 요청")
    data class Questions(
        @field:Schema(description = "제목", required = true, type = "string")
        @NotBlank(message = "질문 제목을 입력해주세요.")
        val title: String,
        @field:Schema(description = "질문 유형", required = true)
        @NotBlank(message = "질문 유형을 선택해주세요.")
        val type: QuestionType,
        @field:Schema(description = "설명", required = false, type = "string")
        val description: String?,
        @field:Schema(description = "선택지 목록", required = false)
        @NotBlank(message = "선택지를 입력해주세요.")
        val options: List<Options>,
        @field:Schema(description = "필수 여부", required = true, type = "boolean")
        @NotNull(message = "필수 여부를 선택해주세요.")
        val isRequired: Boolean,
        @field:Schema(description = "순서", required = true, type = "int")
        @NotNull(message = "순서를 입력해주세요.")
        val orderNumber: Int,
    ) {
        fun toQuestion(survey: Survey) =
            Question(
                title = title,
                type = type,
                description = description ?: "",
                requiredFlag = isRequired,
                orderNumber = orderNumber,
                survey = survey,
            )
    }

    @Schema(description = "선택지 생성 요청")
    data class Options(
        @field:Schema(description = "선택지", required = true, type = "string")
        @NotBlank(message = "선택지를 입력해주세요.")
        val content: String,
        @field:Schema(description = "순서", required = true, type = "int")
        @NotNull(message = "순서를 입력해주세요.")
        val orderNumber: Int,
    ) {
        fun toOption(question: Question) =
            QuestionOption(
                question = question,
                content = content,
                orderNumber = orderNumber,
            )
    }
}
