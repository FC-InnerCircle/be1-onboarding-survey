package org.example.survey.dto

data class OptionRequest(
    val optionId: Long,
    val content: String,
    val seq: Int,
)
