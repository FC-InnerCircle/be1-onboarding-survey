package com.innercircle.onboardingservey.domain.model.entity;

import com.innercircle.onboardingservey.domain.model.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "answer_question")
@Getter
@NoArgsConstructor
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionAnswerId;

    private Long userId;
    private QuestionType questionType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public QuestionAnswer(
        final Long userId,
        final QuestionType questionType,
        final Answer answer,
        final Question question
    ) {
        this.userId = userId;
        this.questionType = questionType;
        this.answer = answer;
        this.question = question;
        valid();
    }



    private void valid() {
        Assert.notNull(userId, "usserId must not be null");
        Assert.notNull(answer, "answer must not be null");
        Assert.notNull(question, "question must not be null");
    }
}
