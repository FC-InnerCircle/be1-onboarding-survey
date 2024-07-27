package org.example.survey.ui

import org.example.survey.application.FeedbackRegisterService
import org.example.survey.dto.feedback.request.FeedbackRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feedbacks")
class FeedbackController(
    private val feedbackRegisterService: FeedbackRegisterService,
) {
    @PostMapping
    fun registerResponse(
        @RequestBody result: FeedbackRequest,
    ): ResponseEntity<Long> {
        val feedbackId = feedbackRegisterService.register(result.toFeedbackEntity())
        return ResponseEntity.ok(feedbackId)
    }
}
