package lshh.be1onboardingsurvey.survey.domain.command;

public record CreateSurveyCommand(
        String name,
        String description
) {
}
