package fastcampus.innercircle.onboarding.survey.dto.response;

import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestion;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResultType;

import java.util.List;

public record FindQuestionResponse(
        Long id,
        Long version,
        String title,
        String desc,
        boolean isRequired,
        SurveyResultType type,
        Integer position,
        List<FindQuestionOptionResponse> options
) {
    public static FindQuestionResponse from(SurveyQuestion question) {
        List<FindQuestionOptionResponse> options = question.getOptions()
                .stream()
                .map(FindQuestionOptionResponse::from)
                .toList();

        return new FindQuestionResponse(
                question.getId(), question.getVersion(), question.getTitle(),
                question.getDesc(), question.isRequired(), question.getResultType(),
                question.getPosition(), options)
        ;
    }
}
