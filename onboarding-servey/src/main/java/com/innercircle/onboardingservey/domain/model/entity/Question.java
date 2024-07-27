package com.innercircle.onboardingservey.domain.model.entity;



import com.innercircle.onboardingservey.domain.model.QuestionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "questions")
@Getter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String title;
    @Nullable
    private String description;
    private QuestionType questionType;
    private Boolean required;
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyVersion surveyVersion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> questionOptions;

    private Question(
        final String title,
        final String description,
        final QuestionType questionType,
        final Boolean required,
        final Integer displayOrder,
        final SurveyVersion surveyVersion,
        final List<QuestionOption> questionOptions
    ) {
        this.title = title;
        this.description = description;
        this.questionType = questionType;
        this.required = required;
        this.displayOrder = displayOrder;
        this.surveyVersion = surveyVersion;
        this.questionOptions = questionOptions;
        valid();
    }

    private void valid() {

        Assert.hasText(
            this.title,
            "title must not be null"
        );
        Assert.notNull(
            this.questionType,
            "questionType must not be null"
        );
        Assert.notNull(
            this.required,
            "required must not be null"
        );
        Assert.notNull(
            this.surveyVersion,
            "survey must not be null"
        );

        if (this.questionType.isChoice()) {
            Assert.notEmpty(
                questionOptions,
                "questionOptionsDetailResponse must not be empty or null"
            );
        }
    }


    public static Question shortText(
        final String title,
        final String description,
        final Boolean required,
        final Integer displayOrder,
        final SurveyVersion surveyVersion
    ) {
        return new Question(
            title,
            description,
            QuestionType.SHORT_TEXT,
            required,
            displayOrder,
            surveyVersion,
            new ArrayList<>()
        );
    }

    public static Question longText(
        final String title,
        final String description,
        final Boolean required,
        final Integer displayOrder,
        final SurveyVersion surveyVersion
    ) {
        return new Question(
            title,
            description,
            QuestionType.LONG_TEXT,
            required,
            displayOrder,
            surveyVersion,
            new ArrayList<>()
        );
    }

    public static Question singleChoice(
        final String title,
        final String description,
        final Boolean required,
        final Integer displayOrder,
        final SurveyVersion surveyVersion,
        final List<QuestionOption> questionOptionList
    ) {
        return new Question(
            title,
            description,
            QuestionType.SINGLE_CHOICE,
            required,
            displayOrder,
            surveyVersion,
            questionOptionList
        );
    }

    public static Question multiChoice(
        final String title,
        final String description,
        final Boolean required,
        final Integer displayOrder,
        final SurveyVersion surveyVersion,
        final List<QuestionOption> questionOptionList
    ) {
        return new Question(
            title,
            description,
            QuestionType.MULTIPLE_CHOICE,
            required,
            displayOrder,
            surveyVersion,
            questionOptionList
        );
    }
}
