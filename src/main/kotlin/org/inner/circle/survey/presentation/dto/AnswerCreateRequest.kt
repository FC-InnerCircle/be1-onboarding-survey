package org.inner.circle.survey.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import org.inner.circle.survey.domain.entity.Answer
import org.inner.circle.survey.domain.entity.AnswerArticle
import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.Survey

class AnswerCreateRequest {
    @Schema(description = "답변 생성 요청")
    data class CreateAnswer(
        @field:Schema(description = "설문지 ID", required = true, type = "long")
        @NotNull(message = "설문지 ID는 필수입니다. ")
        val surveyId: Long,
        @field:Schema(description = "답변자", required = true, type = "string")
        @NotNull(message = "답변자는 필수입니다. ")
        val answerer: String,
        @field:Schema(description = "답변 내용", required = true)
        @NotNull(message = "답변 내용은 필수입니다. ")
        val answerArticles: List<AnswerArticles>,
    ) {
        fun toAnswer(survey: Survey): Answer {
            return Answer(
                survey = survey,
                answerer = answerer,
            )
        }
    }

    @Schema(description = "답변 내용")
    data class AnswerArticles(
        @field:Schema(description = "질문 ID", required = true, type = "long")
        @NotNull(message = "질문 ID는 필수입니다. ")
        val questionId: Long,
        @field:Schema(description = "답변 내용", required = true)
        @NotNull(message = "답변 내용은 필수입니다. ")
        val content: String,
    ) {
        fun toAnswerArticle(
            answer: Answer,
            question: Question,
        ): AnswerArticle {
            return AnswerArticle(
                answer = answer,
                question = question,
                content = content,
            )
        }
    }
}
