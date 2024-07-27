package org.example.survey.ui

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.survey.application.FeedbackViewService
import org.example.survey.dto.feedback.response.AnswerResponse
import org.example.survey.dto.feedback.response.FeedbackResponse
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(FeedbackViewController::class)
class FeedbackViewControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var feedbackViewService: FeedbackViewService

    @Test
    fun `설문 피드백 내역을 조회하여 성공일 경우 200을 리턴한다`() {
        val formId = 1L
        val answerResponse = AnswerResponse(questionId = 1L, content = "Answer text", answerId = 1L)

        val feedbackResponse =
            FeedbackResponse(
                feedbackId = 0L,
                formId = 0L,
                feedbackAt = "2021-10-01T00:00:00",
                answers = listOf(answerResponse),
            )

        whenever(feedbackViewService.getFeedbackHistory(any())).thenReturn(feedbackResponse)

        mockMvc
            .perform(
                get("/feedbacks/forms/$formId/history")
                    .contentType(MediaType.APPLICATION_JSON),
            ).andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(feedbackResponse)))
    }
}
