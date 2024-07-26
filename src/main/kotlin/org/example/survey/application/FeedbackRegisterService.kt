package org.example.survey.application

import org.example.survey.domain.Feedback
import org.example.survey.repository.FeedbackRepository
import org.example.survey.repository.FormRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FeedbackRegisterService(
    private val feedbackRepository: FeedbackRepository,
    private val formRepository: FormRepository,
) {
    @Transactional
    fun register(feedback: Feedback): Long {
        val form =
            formRepository
                .findById(feedback.formId)
                .orElseThrow { throw IllegalArgumentException("설문을 찾을 수 없습니다. formId: ${feedback.formId}") }

        feedback.checkQuestions(form)

        return feedbackRepository.save(feedback).feedbackId
    }
}
