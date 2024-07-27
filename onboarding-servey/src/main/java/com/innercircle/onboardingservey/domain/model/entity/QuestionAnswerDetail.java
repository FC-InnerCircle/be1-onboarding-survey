package com.innercircle.onboardingservey.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "answer_question_datail")
@Getter
@NoArgsConstructor
public class QuestionAnswerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionAnswerDetailId;
    private String textAnswer;
    private Long choiceQuestionOptionId;

    @ManyToOne
    private QuestionAnswer questionAnswer;

    private QuestionAnswerDetail(
        String textAnswer,
        Long choiceQuestionOptionId,
        QuestionAnswer questionAnswer
    ) {
        this.textAnswer = textAnswer;
        this.choiceQuestionOptionId = choiceQuestionOptionId;
        this.questionAnswer = questionAnswer;
    }

    public static QuestionAnswerDetail text(
        String textAnswer,
        QuestionAnswer questionAnswer
    ) {
        return new QuestionAnswerDetail(
            textAnswer,
            null,
            questionAnswer
        );
    }

    public static QuestionAnswerDetail singleChoice(
        Long choiceAnswerId,
        QuestionAnswer questionAnswer
    ) {
        return new QuestionAnswerDetail(
            null,
            choiceAnswerId,
            questionAnswer
        );
    }

    public static List<QuestionAnswerDetail> multiChoice(
        List<Long> choiceAnswerIds,
        QuestionAnswer questionAnswer
    ) {
        return choiceAnswerIds.stream()
            .map(choiceId -> new QuestionAnswerDetail(null,
                choiceId,
                questionAnswer
            ))
            .toList();
    }
}
