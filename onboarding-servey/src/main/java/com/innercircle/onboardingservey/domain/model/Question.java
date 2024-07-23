package com.innercircle.onboardingservey.domain.model;

import static com.innercircle.onboardingservey.common.ValidationUtils.assertHasCollection;
import static com.innercircle.onboardingservey.common.ValidationUtils.assertHasText;
import static com.innercircle.onboardingservey.common.ValidationUtils.assertNotNull;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "questions")
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String title;
    @Nullable
    private String description;
    private QuestionType questionType;
    private Boolean required;

    @ManyToOne(fetch = FetchType.LAZY)
    private Survey survey;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> questionOptions;

    public Question(
        final String title,
        final String description,
        final QuestionType questionType,
        final Boolean required,
        final Survey survey,
        final List<QuestionOption> questionOptions
    ) {
        this.title = title;
        this.description = description;
        this.questionType = questionType;
        this.required = required;
        this.survey = survey;
        this.questionOptions = questionOptions;
        valid();
    }

    private void valid() {
        assertHasText(this.title, "title must not be null");
        assertNotNull(this.questionType, "questionType must not be null");
        assertNotNull(this.required, "required must not be null");
        assertNotNull(this.survey, "survey must not be null");

        if (this.questionType.isChoice()) {
            assertHasCollection(
                questionOptions, "questionOptionsDetailResponse must not be empty or null");
        }
        if (this.questionType.isText()) {
            assertHasText(this.description, "describe must not be null");
        }
    }


    public static Question shortText(
        final String title,
        final String description,
        final Boolean required,
        final Survey survey
    ) {
        return new Question(title, description, QuestionType.SHORT_TEXT, required, survey,
                            new ArrayList<>()
        );
    }

    public static Question longText(
        final String title,
        final String description,
        final Boolean required,
        final Survey survey
    ) {
        return new Question(title, description, QuestionType.LONG_TEXT, required, survey,
                            new ArrayList<>()
        );
    }

    public static Question singleChoice(
        final String title,
        final Boolean required,
        final Survey survey,
        final List<QuestionOption> questionOptionList
    ) {
        return new Question(title, null, QuestionType.SINGLE_CHOICE, required, survey,
                            questionOptionList
        );
    }

    public static Question multiChoice(
        final String title,
        final Boolean required,
        final Survey survey,
        final List<QuestionOption> questionOptionList
    ) {
        return new Question(title, null, QuestionType.MULTIPLE_CHOICE, required, survey,
                            questionOptionList
        );
    }

    public Question update(Question question) {
        this.title = question.getTitle();
        this.description = question.getDescription();
        this.questionType = question.getQuestionType();
        this.required = question.getRequired();
        this.survey = question.getSurvey();
        this.questionOptions = update(question.getQuestionOptions());
        valid();
        return this;
    }

    private List<QuestionOption> update(List<QuestionOption> questionOptions) {
        if (CollectionUtils.isEmpty(questionOptions)) {
            this.questionOptions = new ArrayList<>();
            return this.questionOptions;
        }
        if (CollectionUtils.isEmpty(this.questionOptions)) {
            this.questionOptions = new ArrayList<>();
        }
        final Map<Long, QuestionOption> beforeQuestionOptionMap = this.questionOptions.stream()
            .collect(Collectors.toMap(QuestionOption::getQuestionOptionId, Function.identity()));
        final Map<Long, QuestionOption> updateQuestionOptionMap = questionOptions.stream()
            .collect(Collectors.toMap(QuestionOption::getQuestionOptionId, Function.identity()));

        Sets.difference(
                beforeQuestionOptionMap.keySet(),
                updateQuestionOptionMap.keySet()
            )
            .stream()
            .map(beforeQuestionOptionMap::get)
            .filter(Objects::nonNull)
            .forEach(this.questionOptions::remove);

        final List<QuestionOption> addQuestionOptions = Sets.difference(
                updateQuestionOptionMap.keySet(), beforeQuestionOptionMap.keySet())
            .stream()
            .map(updateQuestionOptionMap::get)
            .filter(Objects::nonNull)
            .toList();

        this.questionOptions.addAll(addQuestionOptions);

        return this.questionOptions;
    }
}
