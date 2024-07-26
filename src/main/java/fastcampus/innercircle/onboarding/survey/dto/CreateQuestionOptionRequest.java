package fastcampus.innercircle.onboarding.survey.dto;

import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestionOption;

import java.util.Objects;

public record CreateQuestionOptionRequest(
        Integer position,
        String name
) {
    public CreateQuestionOptionRequest {
        Objects.requireNonNull(position, "옵션 순번은 필수입니다.");
        Objects.requireNonNull(name, "옵션 이름은 필수입니다.");
    }

    public SurveyQuestionOption toEntity() {
        return SurveyQuestionOption.builder()
                .name(name())
                .build();
    }
}
