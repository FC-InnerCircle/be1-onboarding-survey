package org.inner.circle.survey.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class SurveyCreateControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

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

    @Test
    @DisplayName("설문조사 질문지 작성 실패")
    fun surveyCraeteRequestFail() {

        val surveyParam = mapOf(
            "title" to null,
        )
        val jsonString = objectMapper.writeValueAsString(surveyParam)
        mockMvc.perform(
            post("/api/survey")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isBadRequest)
    }
}
