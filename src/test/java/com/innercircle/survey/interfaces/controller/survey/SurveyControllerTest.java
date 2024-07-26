package com.innercircle.survey.interfaces.controller.survey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.SurveyItem;
import com.innercircle.survey.domain.survey.SurveyResponse;
import com.innercircle.survey.domain.survey.SurveyService;
import com.innercircle.survey.interfaces.controller.survey.dto.SurveyDto;
import com.innercircle.survey.interfaces.controller.survey.dto.SurveyResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("설문 조사를 등록한다.")
    void registerSurvey() throws Exception {
        // given
        SurveyDto.Request request = SurveyDto.Request.builder()
                .surveyName("현대인 운동 현황 조사")
                .surveyDescription("현대인들은 일주일에 얼마나 운동 하는지 궁금하다!")
                .surveyItems(List.of(
                        SurveyDto.Request.SurveyItemDto.builder()
                                .surveyItemName("헬스")
                                .surveyItemDescription("언더아머 가능??")
                                .itemRequired(true)
                                .inputFormat(SurveyItem.ItemInputFormat.SINGLE_SELECTION)
                                .selectOptions(List.of(
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("헬린이 맞음?")
                                                .build()
                                )).build(),

                        SurveyDto.Request.SurveyItemDto.builder()
                                .surveyItemName("수영")
                                .surveyItemDescription("전투수영 가능??")
                                .itemRequired(true)
                                .inputFormat(SurveyItem.ItemInputFormat.MULTIPLE_SELECTION)
                                .selectOptions(List.of(
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("접영 가능?")
                                                .build(),
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("배영 가능?")
                                                .build(),
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("자유형 가능?")
                                                .build()
                                )).build()
                )).build();

        Survey survey = Survey.builder()
                .surveyId(1L)
                .surveyItems(List.of())
                .build();

        when(surveyService.registerSurvey(request.toCreateCommand())).thenReturn(survey);

        // when // then
        mockMvc.perform(post("/v1/surveys")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.msg").value("OK"))
                .andExpect(jsonPath("$.data.surveyId").value(1L));
    }

    @Test
    @DisplayName("등록된 모든 설문 조사를 조회한다.")
    void getSurveys() throws Exception {
        // given
        Survey survey = Survey.builder()
                .surveyItems(List.of(SurveyItem.builder().build())).build();

        when(surveyService.getSurveys()).thenReturn(List.of(survey));

        // when // then
        mockMvc.perform(get("/v1/surveys"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.msg").value("OK"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("설문조사 응답을 제출한다.")
    void submitSurveyResponse() throws Exception {
        //given
        Survey survey = Survey.builder().surveyId(1L).build();
        SurveyResponseDto.Request request = SurveyResponseDto.Request.builder()
                .surveyItems(List.of(SurveyResponseDto.Request.SurveyResponseItemDto.builder().build()))
                .surveyId(survey.getSurveyId()).build();

        SurveyResponse surveyResponse = SurveyResponse.builder()
                .survey(survey)
                .surveyItem(SurveyItem.builder().build())
                .surveyResponseId(1L).build();

        when(surveyService.submitSurvey(request.toCreateCommand())).thenReturn(List.of(surveyResponse));

        //when //then
        mockMvc.perform(post("/v1/surveys/submit")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.msg").value("OK"))
                .andExpect(jsonPath("$.data").isArray());

    }


}