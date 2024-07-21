package lshh.be1onboardingsurvey.survey.domain.dto;

import java.util.List;

public record SurveyResponseView(
        Long id,
        Long surveyId,
        List<SurveyResponseItemView> items
) {
    public static SurveyResponseView of(Long id, Long surveyId) {
        return new SurveyResponseView(id, surveyId, null);
    }

    public static SurveyResponseView of(Long id, Long surveyId, List<SurveyResponseItemView> items) {
        return new SurveyResponseView(id, surveyId, items);
    }

}
