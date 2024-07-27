package fastcampus.innercircle.onboarding.survey.dto.request;

import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestion;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResponseType;
import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionOptionEmptyException;

import java.util.List;
import java.util.Objects;

public record CreateQuestionRequest(
        Integer position,
        String title,
        String desc,
        boolean isRequired,
        SurveyResponseType type,
        List<CreateQuestionOptionRequest> options
) {
    public CreateQuestionRequest {
        Objects.requireNonNull(position, "질문 순번은 필수입니다.");
        Objects.requireNonNull(title, "질문 제목은 필수입니다.");
        Objects.requireNonNull(desc, "질문 설명은 필수입니다.");
        Objects.requireNonNull(type, "답변 유형은 필수입니다.");

        if (!type.isSubjectiveType()) {
            Objects.requireNonNull(options, "옵션 항목은 필수입니다.");
            if (options.isEmpty()) {
                throw new SurveyQuestionOptionEmptyException("옵션 항목은 최소 하나 이상 등록 되어야 합니다.");
            }
        }
    }

    public SurveyQuestion toEntity() {
        return SurveyQuestion.builder()
                .title(title())
                .desc(desc())
                .isRequired(isRequired())
                .responseType(type())
                .position(position())
                .options(options().stream()
                        .map(CreateQuestionOptionRequest::toEntity)
                        .toList())
                .build();
    }
}
