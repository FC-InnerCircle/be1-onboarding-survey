package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemOption;

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

        var preId = surveyItemOption.getVersion().previous() == null ? null : surveyItemOption.getVersion().previous().getId();

        return new SurveyItemOptionVersionView(surveyItemOption.getId(),
                surveyItemOption.getName(),
                surveyItemOption.getDescription(),
                surveyItemOption.getSequence(),
                surveyItemOption.getVersion().overwritten(),
                preId
        );
    }
}