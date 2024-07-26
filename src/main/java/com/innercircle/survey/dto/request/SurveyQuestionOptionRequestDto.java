package com.innercircle.survey.dto.request;

import com.innercircle.survey.model.Question;
import com.innercircle.survey.model.QuestionOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyQuestionOptionRequestDto {
    private Integer questionOptionOrder;

    private String questionOptionValue;

    public QuestionOption toQuestionOptionEntity(Question question) {
        return QuestionOption.builder()
                .question(question)
                .questionOptionOrder(questionOptionOrder)
                .questionOptionValue(questionOptionValue)
                .version(question.getVersion())
                .build();
    }
}
