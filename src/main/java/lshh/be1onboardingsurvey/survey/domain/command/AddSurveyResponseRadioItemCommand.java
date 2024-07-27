package lshh.be1onboardingsurvey.survey.domain.command;

public record AddSurveyResponseRadioItemCommand(
        Long surveyId,
        Long responseId,
        Long itemId,
        Long value
){
    public AddSurveyResponseRadioItemCommand {
        if(surveyId == null || responseId == null || itemId == null){
            throw new IllegalArgumentException("SurveyId, responseId, itemId must not be null");
        }
    }
}
