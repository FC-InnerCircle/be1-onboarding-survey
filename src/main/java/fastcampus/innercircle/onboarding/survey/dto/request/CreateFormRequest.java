package fastcampus.innercircle.onboarding.survey.dto.request;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionEmptyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record CreateFormRequest(
        String title,
        String desc,
        List<CreateQuestionRequest> questions
) {
    public CreateFormRequest {
        Objects.requireNonNull(title, "설문 제목은 필수입니다.");
        Objects.requireNonNull(desc, "설문 설명은 필수입니다.");
        Objects.requireNonNull(questions, "질문 항목은 필수입니다.");
        if (questions.isEmpty()) {
            throw new SurveyQuestionEmptyException("질문 항목은 최소 하나 이상 등록 되어야 합니다.");
        }
    }

    public SurveyForm toEntity() {
        return SurveyForm.builder()
                .version(1L)
                .title(title())
                .desc(desc())
                .createAt(LocalDateTime.now())
                .questions(questions().stream()
                        .map(CreateQuestionRequest::toEntity)
                        .toList())
                .build();
    }
}
