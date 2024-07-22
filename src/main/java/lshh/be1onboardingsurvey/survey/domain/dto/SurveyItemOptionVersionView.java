package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemOption;

import java.time.LocalDateTime;

public record SurveyItemOptionVersionView(
        Long id,
        String name,
        String description,
        Long sequence,
        LocalDateTime overridden,
        Long preId
) {

    public static SurveyItemOptionVersionView of(SurveyItemOption surveyItemOption) {
        return new SurveyItemOptionVersionView(surveyItemOption.getId(),
                surveyItemOption.getName(),
                surveyItemOption.getDescription(),
                surveyItemOption.getSequence(),
                surveyItemOption.getOverridden(),
                surveyItemOption.getPreId()
        );
    }
}