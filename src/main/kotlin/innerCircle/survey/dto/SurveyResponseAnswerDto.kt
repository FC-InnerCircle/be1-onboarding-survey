package innerCircle.survey.dto

import innerCircle.survey.common.QuestionType

data class SurveyResponseAnswerDto(
    val questionId: Long,
    val questionName: String,
    val answerValue: String?,
    val options: List<QuestionType>?
)
