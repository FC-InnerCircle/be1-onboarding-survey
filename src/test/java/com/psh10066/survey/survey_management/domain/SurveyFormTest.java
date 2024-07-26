package com.psh10066.survey.survey_management.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SurveyFormTest {

    @DisplayName("생성 시 버전 1부터 시작한다.")
    @Test
    void create() {
        // given
        // when
        SurveyForm form = SurveyForm.create("name", "description", Collections.emptyList());

        // then
        assertThat(form.getVersion()).isEqualTo(1L);
    }
}