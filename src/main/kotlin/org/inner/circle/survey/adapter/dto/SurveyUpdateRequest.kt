package org.inner.circle.survey.adapter.dto

import org.inner.circle.survey.common.QuestionType

class SurveyUpdateRequest {

    data class UpdateSurvey(
        val id: Long,
        val title: String?,
        val description: String?,
        val questions: List<Questions>?,
    )

    data class Questions(
        val id: Long,
        val title: String?,
        val type: QuestionType?,
        val description: String?,
        val options: List<Options>?,
        val isRequired: Boolean?,
        val orderNumber: Int?,
    )

    data class Options(
        val id: Long,
        val content: String?,
        val orderNumber: Int?,
    )

}