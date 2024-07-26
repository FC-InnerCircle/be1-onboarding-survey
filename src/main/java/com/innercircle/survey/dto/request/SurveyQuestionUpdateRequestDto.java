package com.innercircle.survey.dto.request;

import com.innercircle.survey.model.Question;
import com.innercircle.survey.model.QuestionOption;
import com.innercircle.survey.model.QuestionType;
import com.innercircle.survey.model.Survey;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyQuestionUpdateRequestDto {
    private String question;

    private String description;

    private String questionType;

    private Boolean isRequired;

    private Integer questionOrder;

    private List<SurveyQuestionOptionRequestDto> questionOptions;

    public Question toQuestionEntity(Survey survey) {
        return Question.builder()
                .survey(survey)
                .question(question)
                .description(description)
                .questionType(QuestionType.valueOf(questionType))
                .isRequired(isRequired)
                .questionOrder(questionOrder)
                .version(survey.getVersion())
                .build();
    }

    public List<QuestionOption> toQuestionOptionEntities(Question question) {
        if (CollectionUtils.isEmpty(questionOptions)) {
            return List.of();
        }
        return questionOptions.stream()
                .map(questionOption -> QuestionOption.builder()
                        .question(question)
                        .questionOptionOrder(questionOption.getQuestionOptionOrder())
                        .questionOptionValue(questionOption.getQuestionOptionValue())
                        .version(question.getVersion())
                        .build())
                .toList();
    }
}
