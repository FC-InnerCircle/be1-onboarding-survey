package com.psh10066.survey.survey_management.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SurveyQuestionTest {

    @DisplayName("단답형은 선택 리스트가 있어도 null로 저장한다.")
    @Test
    void surveyQuestionType_short() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.SHORT;
        List<SurveySelectInput> surveySelectInputs = Collections.emptyList();

        // when
        SurveyQuestion surveyQuestion = new SurveyQuestion("name", "description", surveyQuestionType, surveySelectInputs, true);

        // then
        assertThat(surveyQuestion.selectInputs()).isNull();
    }

    @DisplayName("장문형은 선택 리스트가 있어도 null로 저장한다.")
    @Test
    void surveyQuestionType_long() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.LONG;
        List<SurveySelectInput> surveySelectInputs = Collections.emptyList();

        // when
        SurveyQuestion surveyQuestion = new SurveyQuestion("name", "description", surveyQuestionType, surveySelectInputs, true);

        // then
        assertThat(surveyQuestion.selectInputs()).isNull();
    }

    @DisplayName("단일 선택 리스트는 선택 리스트를 필수로 입력해야 한다.")
    @Test
    void surveyQuestionType_radio() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.RADIO;
        List<SurveySelectInput> surveySelectInputs = Collections.emptyList();

        // when
        // then
        assertThatThrownBy(() ->
            new SurveyQuestion("name", "description", surveyQuestionType, surveySelectInputs, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다중 선택 리스트는 선택 리스트를 필수로 입력해야 한다.")
    @Test
    void surveyQuestionType_checkbox() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.CHECKBOX;
        List<SurveySelectInput> surveySelectInputs = Collections.emptyList();

        // when
        // then
        assertThatThrownBy(() ->
            new SurveyQuestion("name", "description", surveyQuestionType, surveySelectInputs, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}