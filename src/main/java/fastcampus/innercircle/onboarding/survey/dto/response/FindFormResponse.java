package fastcampus.innercircle.onboarding.survey.dto.response;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateQuestionRequest;

import java.util.List;

public record FindFormResponse(
        Long id,
        Long version,
        String title,
        String desc,
        List<FindQuestionResponse> questions
) {
    public static FindFormResponse from(SurveyForm form) {
        List<FindQuestionResponse> questions = form.getQuestions()
                .stream()
                .map(FindQuestionResponse::from)
                .toList();
        return new FindFormResponse(form.getId(), form.getVersion(), form.getTitle(), form.getDesc(), questions);
    }
}
