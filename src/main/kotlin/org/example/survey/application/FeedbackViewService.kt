package org.example.survey.application

import org.example.survey.dto.feedback.response.AnswerResponse
import org.example.survey.dto.feedback.response.FeedbackResponse
import org.example.survey.repository.FeedbackHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackViewService(
    private val feedbackHistoryRepository: FeedbackHistoryRepository,
) {
    fun getFeedbackHistory(formId: Long): FeedbackResponse? {
        val feedbackHistory =
            feedbackHistoryRepository
                .findByFormId(formId)
                .maxByOrNull { it.feedbackHistoryId }
                ?: throw IllegalArgumentException("피드백 히스토리를 찾을 수 없습니다. formId: $formId")

        val answers = feedbackHistory.answers
        return answers?.let { parseFeedbackString(it) }
    }
}

private fun parseFeedbackString(feedbackString: String): FeedbackResponse {
    val feedbackAtRegex = """feedbackAt=([^,]+),""".toRegex()
    val formIdRegex = """formId=(\d+),""".toRegex()
    val feedbackIdRegex = """feedbackId=(\d+)""".toRegex()
    val answerRegex = """Answer\(questionId=(\d+), content=([^,]+), answerId=(\d+)\)""".toRegex()

    val feedbackAt = feedbackAtRegex.find(feedbackString)?.groupValues?.get(1) ?: ""
    val formId =
        formIdRegex
            .find(feedbackString)
            ?.groupValues
            ?.get(1)
            ?.toLong() ?: 0
    val feedbackId =
        feedbackIdRegex
            .find(feedbackString)
            ?.groupValues
            ?.get(1)
            ?.toLong() ?: 0

    val answers = mutableListOf<AnswerResponse>()
    for (match in answerRegex.findAll(feedbackString)) {
        val questionId = match.groupValues[1].toLong()
        val content = match.groupValues[2]
        val answerId = match.groupValues[3].toLong()
        answers.add(AnswerResponse(questionId, content, answerId))
    }

    return FeedbackResponse(feedbackAt, formId, answers, feedbackId)
}
