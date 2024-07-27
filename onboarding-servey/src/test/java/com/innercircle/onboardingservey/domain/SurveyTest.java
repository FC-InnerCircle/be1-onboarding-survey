package com.innercircle.onboardingservey.domain;

import static org.assertj.core.api.Assertions.*;

import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SurveyTest {

    @Test
    @DisplayName("설문조사를 생성할 수 있다.")
    void create_survey() {
        String title = "이름을 입력해주세요";
        String description = "본인의 이름을 성과 함게 기재해주세요";
        final Survey survey = new Survey();
        final SurveyVersion surveyVersion = new SurveyVersion(
            title,
            description,
            survey
        );

        assertThat(survey).isNotNull();
        assertThat(surveyVersion).isNotNull();
        assertThat(surveyVersion.getTitle()).isEqualTo(title);
        assertThat(surveyVersion.getDescription()).isEqualTo(description);
    }


}
