package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemOption;

public record SurveyItemOptionView(
        Long id,
        String name,
        String description,
        Long sequence
) {

    public static SurveyItemOptionView of(SurveyItemOption surveyItemOption) {
        return new SurveyItemOptionView(surveyItemOption.getId(),
                surveyItemOption.getName(),
                surveyItemOption.getDescription(),
                surveyItemOption.getSequence()
        );
    }
}
