package org.inner.circle.survey.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.inner.circle.survey.application.service.AnswerCreateService
import org.inner.circle.survey.application.service.SurveyUpdateService
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.infrastructure.repository.SurveyRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AnswerCreateApiTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var answerCreateService: AnswerCreateService

    @MockBean
    lateinit var surveyStorage: SurveyStorage

    @Test
    @DisplayName("답변 등록")
    fun answerCreateRequest() {
        val answerParam = mapOf(
            "surveyId" to 1,
            "answerer" to "응답자",
            "answerArticles" to
                listOf(
                    mapOf(
                        "questionId" to 1,
                        "content" to "답변 내용",
                    ),
                ),
        )
        val answerJsonString = objectMapper.writeValueAsString(answerParam)
        mockMvc.perform(
            post("/api/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(answerJsonString),
        )
            .andExpect(status().isCreated)

    }

}