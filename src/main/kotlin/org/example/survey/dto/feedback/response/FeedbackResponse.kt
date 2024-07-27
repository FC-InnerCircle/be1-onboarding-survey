package org.example.survey.dto.feedback.response

data class FeedbackResponse(
    val feedbackAt: String,
    val formId: Int,
    val answers: List<AnswerResponse>,
    val feedbackId: Int,
)
