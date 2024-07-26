package org.inner.circle.survey.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.inner.circle.survey.application.service.AnswerFindService
import org.inner.circle.survey.domain.storage.AnswerStorage
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AnswerFindApiTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var answerFindService: AnswerFindService

    @MockBean
    lateinit var surveyStorage: SurveyStorage

    @MockBean
    lateinit var answerStorage: AnswerStorage

    @Test
    @DisplayName("답변 조회")
    fun answerFindRequest() {
        mockMvc.perform(
            get("/api/answer?surveyId=1")
        )
            .andExpect(status().isOk)

    }

}