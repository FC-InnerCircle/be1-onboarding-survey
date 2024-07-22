package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyResponseItem;

public record SurveyResponseItemDetailView(
        Long id,
        Long surveyResponseId,
        Long surveyItemId,
        String surveyItemName,
        String surveyItemDescription,
        String formType,
        Object value,
        Object valueDetail
) {
    public static SurveyResponseItemDetailView of(SurveyResponseItem responseItem, SurveyItem surveyItem, Object value) {
        Object valueDetail = switch (surveyItem.getFormType()){
            case TEXT, TEXTAREA -> responseItem.getRealValue();
            case RADIO, CHECKBOX -> value;
        };

        return new SurveyResponseItemDetailView(
                responseItem.getId(),
                responseItem.getSurveyResponse().getId(),
                responseItem.getSurveyItemId(),
                surveyItem.getName(),
                surveyItem.getDescription(),
                surveyItem.getFormType().name(),
                responseItem.getRealValue(),
                valueDetail
        );
    }
}
