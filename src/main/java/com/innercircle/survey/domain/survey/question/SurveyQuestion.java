package com.innercircle.survey.domain.survey.question;


import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.Survey;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

//    public void addShortAnswer(SurveyShortAnswer shortAnswer) {
//        shortAnswer.setSurvey(survey);
//        this.shortAnswers.add(shortAnswer);
//    }
//
//    public void addLongAnswer(SurveyLongAnswer longAnswer) {
//        longAnswer.setSurvey(survey);
//        this.longAnswers.add(longAnswer);
//    }
//
//    public void addSingleSelect(SurveySingleSelect singleSelect) {
//        singleSelect.setSurvey(survey);
//        this.singleSelects.add(singleSelect);
//    }
//
//    public void addMultipleSelect(SurveyMultipleSelect multipleSelect) {
//        multipleSelect.setSurvey(survey);
//        this.multipleSelects.add(multipleSelect);
//    }
}
