package fastcampus.innercircle.onboarding.survey.exception;

import java.util.UUID;

public class SurveyFormNotFoundException extends RuntimeException {
    private static final String SURVEY_FORM_NOTFOUND_MESSAGE =  "[%s]에 해당하는 폼을 찾을 수 없습니다.";

    public SurveyFormNotFoundException(String message) {
        super(message);
    }

    public SurveyFormNotFoundException(UUID formId) {
        super(String.format(SURVEY_FORM_NOTFOUND_MESSAGE, formId));
    }
}
