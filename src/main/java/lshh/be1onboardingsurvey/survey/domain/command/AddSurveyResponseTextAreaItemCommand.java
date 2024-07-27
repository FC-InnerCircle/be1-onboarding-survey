package lshh.be1onboardingsurvey.survey.domain.command;

public record AddSurveyResponseTextAreaItemCommand(
        Long surveyId,
        Long responseId,
        Long itemId,
        String value
){
    public AddSurveyResponseTextAreaItemCommand {
        if(surveyId == null || responseId == null || itemId == null || value == null){
            throw new IllegalArgumentException("SurveyId, responseId, itemId must not be null");
        }
    }
}
