package innerCircle.survey.dto

data class SurveyResponseRequest(
    val surveyId: Long,
    val responses: List<QuestionResponse>
)