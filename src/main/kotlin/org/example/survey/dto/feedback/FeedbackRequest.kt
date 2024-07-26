package org.example.survey.dto.feedback

import org.example.survey.domain.Feedback

data class FeedbackRequest(
    val formId: Long,
    val answers: List<AnswerRequest>,
) {
    fun toFeedbackEntity(): Feedback {
        val feedback =
            Feedback(
                formId = formId,
            )
        answers.forEach {
            feedback.addAnswer(it.toAnswerEntity())
        }
        return feedback
    }
}
