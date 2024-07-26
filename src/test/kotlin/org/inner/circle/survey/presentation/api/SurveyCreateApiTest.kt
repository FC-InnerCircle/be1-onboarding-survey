package org.inner.circle.survey.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.inner.circle.survey.application.service.SurveyCreateService
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class SurveyCreateApiTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var surveyCreateService: SurveyCreateService

    @MockBean
    lateinit var surveyStorage: SurveyStorage


    @Test
    @DisplayName("설문조사 질문지 작성")
    fun surveyCraeteRequest() {
        val surveyParam = SurveyTestSetup().initSurveyCreateParameter()
        val jsonString = objectMapper.writeValueAsString(surveyParam)
        mockMvc.perform(
            post("/api/survey")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isCreated)
    }
}
