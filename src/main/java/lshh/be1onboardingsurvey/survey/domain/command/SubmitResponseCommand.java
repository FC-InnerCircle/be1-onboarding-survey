package lshh.be1onboardingsurvey.survey.domain.command;

public record SubmitResponseCommand (
    Long responseId
){
    public SubmitResponseCommand {
        if (responseId == null) {
            throw new IllegalArgumentException("responseId is null");
        }
    }
}
