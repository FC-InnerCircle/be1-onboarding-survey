package com.innercircle.survey.dto.response;

import com.innercircle.survey.model.Question;
import com.innercircle.survey.model.QuestionOption;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyQuestionResponseDto {
    private String question;

    private String questionType;

    private Boolean isRequired;

    private Integer questionOrder;

    private List<SurveyQuestionOptionResponseDto> questionOptions;

    public static SurveyQuestionResponseDto fromEntity(Question question) {
        return SurveyQuestionResponseDto.builder()
                .question(question.getQuestion())
                .questionType(question.getQuestionType().name())
                .isRequired(question.getIsRequired())
                .questionOrder(question.getQuestionOrder())
                .questionOptions(fromQuestionOptionEntities(question.getQuestionOptions()))
                .build();
    }

    private static List<SurveyQuestionOptionResponseDto> fromQuestionOptionEntities(List<QuestionOption> questionOptions) {
        if (CollectionUtils.isEmpty(questionOptions)) {
            return List.of();
        }
        return questionOptions.stream()
                .map(SurveyQuestionOptionResponseDto::fromQuestionOptionEntity)
                .toList();
    }
}
