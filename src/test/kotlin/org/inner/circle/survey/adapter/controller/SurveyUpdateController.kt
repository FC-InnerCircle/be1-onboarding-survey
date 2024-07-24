package org.inner.circle.survey.adapter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class SurveyUpdateController {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("설문조사 질문지 수정")
    fun surveyUpdateRequest() {
        val initialSurveyParam = SurveyTestSetup().initSurveyCreateParameter()
        val initialJsonString = objectMapper.writeValueAsString(initialSurveyParam)
        mockMvc.perform(post("/api/survey")
            .contentType(MediaType.APPLICATION_JSON)
            .content(initialJsonString))
            .andExpect(status().isCreated)

        // Step 2: Update the inserted data
        val updatedSurveyParam = initialSurveyParam.toMutableMap().apply {
            put("title", "수정된 설문조사 제목")
        }
        val updatedJsonString = objectMapper.writeValueAsString(updatedSurveyParam)
        mockMvc.perform(patch("/survey/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedJsonString))
            .andExpect(status().isOk)

        // Step 3: Verify the update
        mockMvc.perform(get("/survey/{id}", 1)) // Assuming the ID of the inserted survey is 1
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("수정된 설문조사 제목"))
            .andExpect(jsonPath("$.description").value(initialSurveyParam["description"]))
            .andExpect(jsonPath("$.writer").value(initialSurveyParam["writer"]))
    }

}