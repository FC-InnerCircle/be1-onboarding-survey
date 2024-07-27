package fastcampus.innercircle.onboarding.survey.dto.request;
import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestion;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResultDetail;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResultDetailValue;
import fastcampus.innercircle.onboarding.survey.exception.InvalidSurveyResultDataException;

import java.util.List;
import java.util.Objects;

public record CreateQuestionResultRequest(
        Long questionId,
        List<String> values
) {
    public CreateQuestionResultRequest {
        Objects.requireNonNull(questionId, "질문 ID 항목은 필수입니다.");
        Objects.requireNonNull(questionId, "답변 목록 항목은 필수입니다.");
    }

    public SurveyResultDetail toEntity(final SurveyQuestion question) {
        if (!Objects.equals(this.questionId, question.getId())) { // TODO questions의 순서를 보장할 수 있게 position을 이용해서 정렬하는 로직을 추가하자, 혹은 조회 시 정렬
            throw new InvalidSurveyResultDataException("신청 폼과 제출한 데이터의 형식이 맞지 않습니다.");
        }

        return SurveyResultDetail.builder()
                .question(question)
                .values(values.stream()
                        .map(SurveyResultDetailValue::new)
                        .toList())
                .build();
    }
}
