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
public class SurveyQuestionCreateRequestDto {
    private String question;

    private String description;

    private String questionType;

    private Boolean isRequired;

    private Integer questionOrder;

    private List<SurveyQuestionOptionRequestDto> questionOptions;

    public Question toQuestionEntity(Survey survey) {
        Question question = Question.builder()
                .survey(survey)
                .question(this.question)
                .description(description)
                .questionType(QuestionType.valueOf(questionType))
                .isRequired(isRequired)
                .questionOrder(questionOrder)
                .version(0)
                .build();
        if (!CollectionUtils.isEmpty(questionOptions)) {
            List<QuestionOption> questionOptionEntities = questionOptions.stream()
                    .map(questionOption -> questionOption.toQuestionOptionEntity(question))
                    .toList();
            question.setQuestionOptions(questionOptionEntities);
        }
        return question;
    }
}
