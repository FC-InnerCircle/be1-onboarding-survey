package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemOption;

public record AddSurveyItemOptionCommand(
        Long surveyId,
        Long itemId,
        String name,
        String description,
        Long sequence
) {
    public AddSurveyItemOptionCommand {
        if(surveyId == null || itemId == null){
            throw new IllegalArgumentException("SurveyId, itemId must not be null");
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
