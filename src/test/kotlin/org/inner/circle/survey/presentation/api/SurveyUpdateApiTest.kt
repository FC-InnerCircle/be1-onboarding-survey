package org.inner.circle.survey.presentation.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.inner.circle.survey.application.service.SurveyUpdateService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class SurveyUpdateApiTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var surveyUpdateService: SurveyUpdateService

    @Test
    @DisplayName("설문조사 질문지 수정")
    fun surveyUpdateRequest() {
        val initialSurveyParam = SurveyTestSetup().initSurveyCreateParameter()

        val updatedSurveyParam =
            initialSurveyParam.toMutableMap().apply {
                put("title", "수정된 설문조사 제목")
            }
        val updatedJsonString = objectMapper.writeValueAsString(updatedSurveyParam)
        mockMvc.perform(
            patch("/api/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJsonString),
        )
            .andExpect(status().isOk)

    }
}
