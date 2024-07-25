package org.example.survey.dto

data class QuestionRequest(
    val name: String,
    val description: String,
    val inputType: String,
    val required: Boolean,
    val options: List<OptionRequest> = listOf(),
)
