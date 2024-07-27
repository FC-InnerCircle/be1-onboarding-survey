package org.example.survey.ui

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.survey.application.FeedbackRegisterService
import org.example.survey.dto.feedback.request.AnswerRequest
import org.example.survey.dto.feedback.request.FeedbackRequest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(FeedbackController::class)
class FeedbackControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var feedbackRegisterService: FeedbackRegisterService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `설문 피드백을 등록하고 200 응답을 리턴한다`() {
        val feedbackId = 1L
        val feedbackRequest =
            FeedbackRequest(
                formId = 1L,
                answers = listOf(AnswerRequest("Answer text", 1L)),
            )
        whenever(feedbackRegisterService.register(any())).thenReturn(feedbackId)

        mockMvc
            .perform(
                post("/feedbacks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(feedbackRequest)),
            ).andExpect(status().isOk)
            .andExpect(content().string(feedbackId.toString()))
    }
}
