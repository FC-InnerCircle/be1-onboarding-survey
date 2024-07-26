package innerCircle.survey.dto

data class CreateSurveyRequest(
    val name: String,
    val description: String,
    val questions: List<QuestionRequest>
)