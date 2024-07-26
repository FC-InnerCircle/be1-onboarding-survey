package innerCircle.survey.dto

data class UpdateSurveyRequest(
    val name: String,
    val description: String,
    val questions: List<QuestionUpdateRequest>
)