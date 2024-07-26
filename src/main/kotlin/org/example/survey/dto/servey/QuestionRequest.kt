package org.example.survey.dto.servey

data class QuestionRequest(
    val questionId: Long,
    val name: String,
    val description: String,
    val inputType: String,
    val required: Boolean,
    val options: List<OptionRequest> = listOf(),
)
