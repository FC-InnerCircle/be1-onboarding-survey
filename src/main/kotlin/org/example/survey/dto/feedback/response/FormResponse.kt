package org.example.survey.dto.feedback.response

data class FormResponse(
    val formId: Int,
    val name: String,
    val description: String,
    val questions: List<QuestionResponse>,
)
