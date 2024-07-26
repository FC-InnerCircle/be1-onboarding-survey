package com.innercircle.survey.integration;

import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.SurveyItem;
import com.innercircle.survey.domain.survey.SurveyRepository;
import com.innercircle.survey.domain.survey.SurveyService;
import com.innercircle.survey.interfaces.controller.survey.dto.SurveyDto;
import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.innercircle.survey.domain.survey.SurveyItem.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SurveyIntegrationTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyRepository surveyRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        surveyRepository.deleteAll();
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
                                .surveyItemName("헬스")
                                .surveyItemDescription("언더아머 가능??")
                                .itemRequired(true)
                                .inputFormat(ItemInputFormat.SINGLE_SELECTION)
                                .selectOptions(List.of(
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("헬린이 맞음?")
                                                .build()
                                )).build(),

                        SurveyDto.Request.SurveyItemDto.builder()
                                .surveyItemName("수영")
                                .surveyItemDescription("전투수영 가능??")
                                .itemRequired(true)
                                .inputFormat(ItemInputFormat.MULTIPLE_SELECTION)
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

        // when
        Survey survey = surveyService.registerSurvey(request.toCreateCommand());

        // then
        assertThat(survey.getSurveyName()).isEqualTo("현대인 운동 현황 조사");
    }

    @Test
    @DisplayName("등록된 모든 설문 조사를 조회한다.")
    void getSurveys() {
        //given
        SurveyDto.Request request = SurveyDto.Request.builder()
                .surveyName("현대인 운동 현황 조사")
                .surveyDescription("현대인들은 일주일에 얼마나 운동 하는지 궁금하다!")
                .surveyItems(List.of(
                        SurveyDto.Request.SurveyItemDto.builder()
                                .surveyItemName("헬스")
                                .surveyItemDescription("언더아머 가능??")
                                .itemRequired(true)
                                .inputFormat(ItemInputFormat.SINGLE_SELECTION)
                                .selectOptions(List.of(
                                        SurveyDto.Request.SurveyItemDto.SelectOptionDto.builder()
                                                .name("헬린이 맞음?")
                                                .build()
                                )).build(),

                        SurveyDto.Request.SurveyItemDto.builder()
                                .surveyItemName("수영")
                                .surveyItemDescription("전투수영 가능??")
                                .itemRequired(true)
                                .inputFormat(ItemInputFormat.MULTIPLE_SELECTION)
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

        surveyService.registerSurvey(request.toCreateCommand());

        //when
        List<Survey> result = surveyService.getSurveys();

        //then
        assertThat(result).hasSize(1);
    }

}
