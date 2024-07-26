package com.innercircle.survey.dto.response;

import com.innercircle.survey.model.Answer;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerResponseDto {
    private Integer questionOrder;
    private String questionType;
    private String answerValue;
    private Integer answerSingleOption;
    private String answerMultipleOptions;

    public static AnswerResponseDto fromAnswerEntity(Answer answer) {
        return AnswerResponseDto.builder()
                .questionOrder(answer.getQuestionOrder())
                .questionType(answer.getQuestionType().name())
                .answerValue(answer.getAnswerValue())
                .answerSingleOption(answer.getAnswerSingleOption())
                .answerMultipleOptions(answer.getAnswerMultipleOptions())
                .build();
    }
}
