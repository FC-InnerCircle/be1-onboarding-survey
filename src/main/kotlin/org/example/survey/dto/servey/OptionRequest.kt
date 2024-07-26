package org.example.survey.dto.servey

data class OptionRequest(
    val optionId: Long,
    val content: String,
    val seq: Int,
)
