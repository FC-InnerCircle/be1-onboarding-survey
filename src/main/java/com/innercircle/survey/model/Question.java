package com.innercircle.survey.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private String question;

    private String description;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Boolean isRequired;

    @Setter
    @OneToMany(mappedBy = "question")
    private List<QuestionOption> questionOptions = new ArrayList<>();

    private Integer questionOrder;

    private Integer version;

    @Builder
    private Question(Survey survey, String question, String description, QuestionType questionType, Boolean isRequired, Integer questionOrder, Integer version) {
        this.survey = survey;
        this.question = question;
        this.description = description;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.questionOrder = questionOrder;
        this.version = version;
    }

    public void update(String question, String description, QuestionType questionType, Boolean isRequired, Integer version) {
        this.question = question;
        this.description = description;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.version = version;
    }
}
