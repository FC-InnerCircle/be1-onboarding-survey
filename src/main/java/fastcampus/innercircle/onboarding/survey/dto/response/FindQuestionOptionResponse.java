package fastcampus.innercircle.onboarding.survey.dto.response;

import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestionOption;

public record FindQuestionOptionResponse(
        Long id,
        String name
) {
    public static FindQuestionOptionResponse from(SurveyQuestionOption option) {
        return new FindQuestionOptionResponse(option.getId(), option.getName());
    }
}
