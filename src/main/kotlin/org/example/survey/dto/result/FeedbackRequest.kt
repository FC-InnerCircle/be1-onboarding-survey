package org.example.survey.dto.result

import org.example.survey.domain.Feedback

data class FeedbackRequest(
    val formId: Long,
    val answers: List<AnswerRequest>,
) {
    fun toFeedbackEntity(): Feedback =
        Feedback(
            formId = formId,
            answers = answers.map { it.toAnswerEntity() },
        )
}
