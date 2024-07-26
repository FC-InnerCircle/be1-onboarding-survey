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
        List<String> surveySelectInputs = List.of("그렇다", "아니다");

        // when
        SurveyQuestion surveyQuestion = SurveyQuestion.create("name", "description", surveyQuestionType, surveySelectInputs, true);

        // then
        assertThat(surveyQuestion.selectInputs()).isNull();
    }

    @DisplayName("장문형은 선택 리스트가 있어도 null로 저장한다.")
    @Test
    void surveyQuestionType_long() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.LONG;
        List<String> surveySelectInputs = List.of("그렇다", "아니다");

        // when
        SurveyQuestion surveyQuestion = SurveyQuestion.create("name", "description", surveyQuestionType, surveySelectInputs, true);

        // then
        assertThat(surveyQuestion.selectInputs()).isNull();
    }

    @DisplayName("단일 선택 리스트는 선택 리스트를 필수로 입력해야 한다.")
    @Test
    void surveyQuestionType_radio() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.RADIO;
        List<String> surveySelectInputs = Collections.emptyList();

        // when
        // then
        assertThatThrownBy(() ->
            SurveyQuestion.create("name", "description", surveyQuestionType, surveySelectInputs, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다중 선택 리스트는 선택 리스트를 필수로 입력해야 한다.")
    @Test
    void surveyQuestionType_checkbox() {
        // given
        SurveyQuestionType surveyQuestionType = SurveyQuestionType.CHECKBOX;
        List<String> surveySelectInputs = Collections.emptyList();

        // when
        // then
        assertThatThrownBy(() ->
            SurveyQuestion.create("name", "description", surveyQuestionType, surveySelectInputs, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 선택 리스트의 inputValue를 0부터 설정한다.")
    @Test
    void selectInputWithValue() {
        // given
        List<String> surveySelectInputs = List.of("그렇다", "아니다");

        // when
        SurveyQuestion surveyQuestion = SurveyQuestion.create("name", "description", SurveyQuestionType.CHECKBOX, surveySelectInputs, true);

        // then
        assertThat(surveyQuestion.selectInputs().get(0).inputValue()).isEqualTo(0);
        assertThat(surveyQuestion.selectInputs().get(0).text()).isEqualTo("그렇다");
        assertThat(surveyQuestion.selectInputs().get(1).inputValue()).isEqualTo(1);
        assertThat(surveyQuestion.selectInputs().get(1).text()).isEqualTo("아니다");
    }
}