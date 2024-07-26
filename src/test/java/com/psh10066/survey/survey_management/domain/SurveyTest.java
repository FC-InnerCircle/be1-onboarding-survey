package com.psh10066.survey.survey_management.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SurveyTest {

    @DisplayName("생성 시 ID가 자동으로 부여된다.")
    @Test
    void create() {
        // given
        // when
        Survey survey = Survey.create(SurveyForm.create("name", "description", Collections.emptyList()));

        // then
        assertThat(survey.getId()).isNotNull();
        assertThat(survey.getId().value()).isNotNull();
    }
}