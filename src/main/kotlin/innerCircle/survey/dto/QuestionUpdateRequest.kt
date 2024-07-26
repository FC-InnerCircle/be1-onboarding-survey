package innerCircle.survey.dto

import innerCircle.survey.common.QuestionType

data class QuestionUpdateRequest(
    val id: Long? = null,
    val name: String,
    val description: String,
    val questionType: QuestionType,
    val isRequired: Boolean,
    val options: List<String>?
)