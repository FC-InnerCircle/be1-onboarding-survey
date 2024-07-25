package org.example.survey.ui

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.survey.application.FormRegisterService
import org.example.survey.dto.SurveyRequest
import org.junit.jupiter.api.BeforeEach
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

@WebMvcTest(SurveyController::class)
class SurveyControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var formRegisterService: FormRegisterService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var surveyRequest: SurveyRequest

    @BeforeEach
    fun setup() {
        surveyRequest =
            SurveyRequest(
                name = "Test Survey",
                description = "This is a test survey",
                questions = listOf(),
            )
    }

    @Test
    fun `설문을 생성하면 응답값으로 200을 리턴한다`() {
        val expectedFormId = 1L
        whenever(formRegisterService.register(any()))
            .thenReturn(expectedFormId)

        mockMvc
            .perform(
                post("/surveys")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(surveyRequest)),
            ).andExpect(status().isOk)
            .andExpect(content().string(expectedFormId.toString()))
    }
}
