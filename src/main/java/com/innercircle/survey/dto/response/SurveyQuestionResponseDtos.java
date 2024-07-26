package com.innercircle.survey.dto.response;

import com.innercircle.survey.model.Question;
import lombok.Getter;

import java.util.List;

@Getter
public class SurveyQuestionResponseDtos {
    private final List<SurveyQuestionResponseDto> questions;

    protected SurveyQuestionResponseDtos(List<Question> questions) {
        this.questions = makeDtosFrom(questions);
    }

    private List<SurveyQuestionResponseDto> makeDtosFrom(List<Question> questions) {
        return questions.stream()
                .map(SurveyQuestionResponseDto::fromEntity)
                .toList();
    }

    public static SurveyQuestionResponseDtos fromEntity(List<Question> questions) {
        return new SurveyQuestionResponseDtos(questions);
    }
}
