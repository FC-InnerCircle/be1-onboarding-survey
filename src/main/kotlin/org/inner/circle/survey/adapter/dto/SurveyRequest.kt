package org.inner.circle.survey.adapter.dto

import org.inner.circle.survey.common.QuestionType
import org.inner.circle.survey.domain.Question
import org.inner.circle.survey.domain.QuestionOption
import org.inner.circle.survey.domain.Survey

class SurveyRequest {
    data class CreateSurvey(
        val title: String,
        val description: String?,
        val questions: List<Questions>,
        val writer: String,
    ) {
        fun toSurvey() =
            Survey(
                title = title,
                description = description ?: "",
                writer = writer,
            )
    }

    data class UpdateSurvey(
        val id: Long,
        val title: String?,
        val description: String?,
        val questions: List<Questions>?,
    )

    data class Questions(
        val id: Long?,
        val title: String,
        val type: QuestionType,
        val description: String?,
        val options: List<Options>,
        val isRequired: Boolean,
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

    data class Options(
        val id: Long?,
        val content: String,
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
