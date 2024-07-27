package org.example.survey.dto.feedback.response

data class QuestionResponse(
    val questionId: Int,
    val name: String,
    val description: String,
    val inputType: String,
    val required: Boolean,
    val options: List<OptionResponse>,
)
