package com.innercircle.survey.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED
)
@Entity
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private Response response;

    private Integer questionOrder;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String answerValue;

    private Integer answerSingleOption;

    private String answerMultipleOptions;

    private Integer version;

    @Builder
    private Answer(Question question, Response response, Integer questionOrder, QuestionType questionType, String answerValue, String answerMultipleOptions, Integer answerSingleOption, Integer version) {
        this.question = question;
        this.response = response;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.answerValue = answerValue;
        this.answerSingleOption = answerSingleOption;
        this.answerMultipleOptions = answerMultipleOptions;
        this.version = version;
    }
}
