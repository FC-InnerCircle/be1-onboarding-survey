package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemOption;

public record UpdateSurveyItemOptionCommand(
        Long surveyId,
        Long itemId,
        Long optionId,
        String name,
        String description,
        Long sequence
) {
    public SurveyItemOption toEntity() {
        return SurveyItemOption.builder()
                .name(this.name)
                .description(this.description)
                .sequence(this.sequence)
                .build();
    }
}
