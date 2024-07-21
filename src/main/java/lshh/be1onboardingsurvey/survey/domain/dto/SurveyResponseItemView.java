package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyResponseItem;

public record SurveyResponseItemView(
        Long id,
        Long surveyResponseId,
        Long surveyItemId,
        Object value
) {
    public static SurveyResponseItemView of(SurveyResponseItem item) {
        return new SurveyResponseItemView(item.getId(), item.getSurveyResponse().getId(), item.getSurveyItemId(), item.getRealValue());
    }
}
