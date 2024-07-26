package org.example.survey.dto.feedback

import org.example.survey.domain.Answer

data class AnswerRequest(
    val content: String,
    val questionId: Long,
) {
    fun toAnswerEntity(): Answer =
        Answer(
            content = this.content,
            questionId = this.questionId,
        )
}
