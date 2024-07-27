package fastcampus.innercircle.onboarding.survey.dto.request;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestion;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResult;
import fastcampus.innercircle.onboarding.survey.exception.InvalidSurveyResultDataException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public record CreateResultRequest(
        List<CreateQuestionResultRequest> questionResults
) {
    public CreateResultRequest {
        Objects.requireNonNull(questionResults, "답변 항목은 필수입니다.");
    }

    public SurveyResult toEntity(final SurveyForm form) {
        List<SurveyQuestion> questions = form.getQuestions();
        if (questionResults.size() != questions.size()) {
            throw new InvalidSurveyResultDataException("질문 갯수와 답변 갯수가 일치하지 않습니다.");
        }

        return SurveyResult.builder()
                .form(form)
                .createAt(LocalDateTime.now())
                .details(IntStream.range(0, questionResults.size())
                        .mapToObj(index -> questionResults.get(index).toEntity(questions.get(index)))
                        .toList())
                .build();
    }
}
