package org.example.survey.ui

import org.example.survey.application.FeedbackViewService
import org.example.survey.dto.feedback.response.FeedbackResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feedbacks")
class FeedbackViewController(
    private val feedbackViewService: FeedbackViewService,
) {
    @GetMapping("/forms/{formId}/history")
    fun getFeedbackHistory(
        @PathVariable formId: Long,
    ): ResponseEntity<FeedbackResponse> = ResponseEntity.ok(feedbackViewService.getFeedbackHistory(formId))
}
