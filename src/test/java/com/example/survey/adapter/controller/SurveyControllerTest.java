package com.example.survey.adapter.controller;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.dto.SurveyItemDto;
import com.example.survey.domain.model.InputType;
import com.example.survey.usecase.SurveyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
@ExtendWith(MockitoExtension.class)
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Autowired
    private ObjectMapper objectMapper;

    private SurveyDto validSurveyDto;
    private SurveyDto invalidSurveyDto;

    @BeforeEach
    void setUp() {
        SurveyItemDto item1 = SurveyItemDto.builder()
            .name("Question 1")
            .description("Description 1")
            .inputType(InputType.SHORT_ANSWER)
            .required(true)
            .build();

        SurveyItemDto item2 = SurveyItemDto.builder()
            .name("Question 2")
            .description("Description 2")
            .inputType(InputType.LONG_ANSWER)
            .required(false)
            .build();

        validSurveyDto = SurveyDto.builder()
            .name("Test Survey")
            .description("Test Description")
            .items(Arrays.asList(item1, item2))
            .build();

        invalidSurveyDto = SurveyDto.builder()
            .name("")
            .description("")
            .items(Arrays.asList(item1, item2))
            .build();
    }

    @Test
    @DisplayName("Create survey with valid data")
    void testCreateSurveyWithValidData() throws Exception {
        when(surveyService.createSurvey(any(SurveyDto.class))).thenReturn(validSurveyDto);

        mockMvc.perform(post("/api/surveys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validSurveyDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.message").value("Survey created successfully"))
            .andExpect(jsonPath("$.data.name").value(validSurveyDto.getName()))
            .andExpect(jsonPath("$.data.items.length()").value(validSurveyDto.getItems().size()));
    }

    @Test
    @DisplayName("Create survey with invalid data")
    void testCreateSurveyWithInvalidData() throws Exception {
        mockMvc.perform(post("/api/surveys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidSurveyDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value("error"))
            .andExpect(jsonPath("$.message").exists());
    }
}
