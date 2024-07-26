package innerCircle.survey.dto

data class SurveyResponseDto(
    val id: Long,
    val submittedAt: String,
    val answers: List<SurveyResponseAnswerDto>
)
