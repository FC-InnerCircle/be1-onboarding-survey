package org.example.survey.application

import org.example.survey.domain.Feedback
import org.example.survey.domain.FeedbackHistory
import org.example.survey.repository.FeedbackHistoryRepository
import org.example.survey.repository.FeedbackRepository
import org.example.survey.repository.FormRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackRegisterService(
    private val feedbackRepository: FeedbackRepository,
    private val formRepository: FormRepository,
    private val feedbackHistoryRepository: FeedbackHistoryRepository,
) {
    @Transactional
    fun register(feedback: Feedback): Long {
        val form =
            formRepository
                .findById(feedback.formId)
                .orElseThrow { throw IllegalArgumentException("설문을 찾을 수 없습니다. formId: ${feedback.formId}") }

        feedback.checkQuestions(form)

        val savedFeedBack = feedbackRepository.save(feedback)

        feedbackHistoryRepository.save(
            FeedbackHistory(savedFeedBack.feedbackId, form.toString(), savedFeedBack.toString()),
        )

        return feedbackRepository.save(feedback).feedbackId
    }
}
