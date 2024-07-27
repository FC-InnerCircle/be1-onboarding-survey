package fastcampus.innercircle.onboarding.survey.exception;

public class SurveyFormUnchangedException extends RuntimeException {
    private static final String SURVEY_FORM_UNCHANGED_MESSAGE =  "기존 폼과 달라진 내용이 없습니다.";

    public SurveyFormUnchangedException() {
        super(SURVEY_FORM_UNCHANGED_MESSAGE);
    }

    public SurveyFormUnchangedException(String message) {
        super(message);
    }
}
