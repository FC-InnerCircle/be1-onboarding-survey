package innerCircle.survey.dto

import innerCircle.survey.common.QuestionType

data class QuestionRequest(
    val name: String,
    val description: String,
    val questionType: QuestionType,
    val isRequired: Boolean,
    val options: List<String>?
)