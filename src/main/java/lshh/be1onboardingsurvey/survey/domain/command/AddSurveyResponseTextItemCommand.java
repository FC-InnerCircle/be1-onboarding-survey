package lshh.be1onboardingsurvey.survey.domain.command;

public record AddSurveyResponseTextItemCommand (
        Long surveyId,
        Long responseId,
        Long itemId,
        String value
){
    public AddSurveyResponseTextItemCommand {
        if(surveyId == null || responseId == null || itemId == null){
            throw new IllegalArgumentException("SurveyId, responseId, itemId must not be null");
        }
    }
}
