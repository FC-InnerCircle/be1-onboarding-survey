package org.example.survey.application

import org.example.survey.domain.Feedback
import org.example.survey.repository.FeedbackRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackRegisterService(
    private val feedbackRepository: FeedbackRepository,
) {
    @Transactional
    fun register(feedback: Feedback): Long = feedbackRepository.save(feedback).feedbackId
}
