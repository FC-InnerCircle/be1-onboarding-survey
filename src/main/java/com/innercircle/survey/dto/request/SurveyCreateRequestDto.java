package com.innercircle.survey.dto.request;

import com.innercircle.survey.model.Question;
import com.innercircle.survey.model.Survey;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyCreateRequestDto {
    @NotBlank(message = "질문 제목을 입력해 주세요.")
    private String title;

    private String description;

    private String questionType;

    private List<SurveyQuestionCreateRequestDto> questions;

    public Survey toSurveyEntity() {
        return Survey.builder()
                .title(title)
                .description(description)
                .build();
    }

    public List<Question> toQuestionEntities(Survey survey) {
        return questions.stream()
                .map(question -> question.toQuestionEntity(survey))
                .toList();
    }
}
