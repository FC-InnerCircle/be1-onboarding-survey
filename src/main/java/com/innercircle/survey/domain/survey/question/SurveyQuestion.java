package com.innercircle.survey.domain.survey.question;


import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.Survey;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "survey_questions")
@Inheritance(strategy = InheritanceType.JOINED)
public class SurveyQuestion extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private String questionText;
    private Long version;
    private Long sequence;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
}
