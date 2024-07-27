package org.example.survey.dto.feedback.response

data class AnswerResponse(
    val questionId: Long,
    val content: String,
    val answerId: Long,
)
