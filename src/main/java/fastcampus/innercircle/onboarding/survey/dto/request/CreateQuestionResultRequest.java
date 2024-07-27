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
        // 답변 내용과 신청 폼의 형태가 동일한지 검사
        if (!Objects.equals(this.questionId, question.getId())) { // TODO questions의 순서를 보장할 수 있게 position을 이용해서 정렬하는 로직을 추가하자, 혹은 조회 시 정렬
            throw new InvalidSurveyResultDataException("신청 폼과 제출한 데이터의 형식이 맞지 않습니다.");
        }

        // 필수 질문 검사
        if (question.isRequired() && values.isEmpty()) {
            throw new InvalidSurveyResultDataException("필수 질문은 넘어갈 수 없습니다.");
        }

        // TODO value 값이 질문 option에 있었는지 검사

        // TODO ResultType에 따른 답변 갯수 검사

        return SurveyResultDetail.builder()
                .question(question)
                .values(values.stream()
                        .map(SurveyResultDetailValue::new)
                        .toList())
                .build();
    }
}
