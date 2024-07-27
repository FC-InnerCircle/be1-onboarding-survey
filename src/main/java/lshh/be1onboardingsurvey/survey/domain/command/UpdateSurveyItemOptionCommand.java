package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemOption;

public record UpdateSurveyItemOptionCommand(
        Long surveyId,
        Long itemId,
        Long optionId,
        String name,
        String description,
        Long sequence
) {
    public UpdateSurveyItemOptionCommand {
        if(surveyId == null || itemId == null || optionId == null){
            throw new IllegalArgumentException("SurveyId, itemId, optionId must not be null");
        }
    }

    public SurveyItemOption toEntity() {
        return SurveyItemOption.builder()
                .name(this.name)
                .description(this.description)
                .sequence(this.sequence)
                .build();
    }
}
