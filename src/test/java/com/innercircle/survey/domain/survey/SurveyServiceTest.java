package com.innercircle.survey.domain.survey;

import com.innercircle.survey.interfaces.controller.survey.dto.SurveyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyValidator surveyValidator;

    @InjectMocks
    private SurveyService surveyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("설문 조사를 등록한다.")
    void registerSurvey() {
        // given
        SurveyDto.Request request = SurveyDto.Request.builder()
                .surveyName("현대인 운동 현황 조사")
                .surveyDescription("현대인들은 일주일에 얼마나 운동 하는지 궁금하다!")
                .surveyItems(List.of(
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

        Survey survey = Survey.builder().surveyId(1L).build();
        SurveyItem surveyItem = SurveyItem.builder().build();

        when(surveyRepository.saveSurvey(any())).thenReturn(Optional.ofNullable(survey));
        when(surveyValidator.checkSaveSurvey(any())).thenReturn(survey);
        when(surveyRepository.saveSurveyItem(any())).thenReturn(Optional.ofNullable(surveyItem));
        when(surveyValidator.checkSaveSurveyItem(any())).thenReturn(surveyItem);

        // when
        Survey result = surveyService.registerSurvey(request.toCreateCommand());

        // then
        assertThat(result.getSurveyId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("등록된 모든 설문 조사를 조회한다.")
    void getSurveys() {
        // given
        Survey survey = Survey.builder().surveyId(1L).build();
        List<Survey> surveys = List.of(survey);
        List<SurveyItem> surveyItems = List.of(SurveyItem.builder().survey(survey).build());
        when(surveyRepository.getSurveys()).thenReturn(surveys);
        when(surveyRepository.getSurveyItems()).thenReturn(surveyItems);

        // when
        List<Survey> result = surveyService.getSurveys();

        // then
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("설문조사 응답을 제출한다.")
    void submitSurveyResponse() {
        //given

        //when

        //then
    }

}