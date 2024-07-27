package org.example.survey.dto.feedback.response

data class AnswerResponse(
    val questionId: Int,
    val content: String,
    val answerId: Int,
)
