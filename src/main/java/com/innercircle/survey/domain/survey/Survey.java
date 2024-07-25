package com.innercircle.survey.domain.survey;

import com.innercircle.survey.common.util.TokenGenerator;
import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.question.SurveyLongAnswer;
import com.innercircle.survey.domain.survey.question.SurveyMultipleSelect;
import com.innercircle.survey.domain.survey.question.SurveyShortAnswer;
import com.innercircle.survey.domain.survey.question.SurveySingleSelect;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "surveys")
public class Survey extends AbstractEntity {

    private static final String SURVEY_PREFIX = "sv_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surveyToken;
    private Long version;
    private ZonedDateTime surveyCreatedAt;

    @OneToMany(mappedBy = "survey")
    private List<SurveyShortAnswer> shortAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveyLongAnswer> longAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveySingleSelect> singleSelects = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveyMultipleSelect> multipleSelects = new ArrayList<>();

    public Survey() {
        this.surveyToken = TokenGenerator.randomCharacterWithPrefix(SURVEY_PREFIX);
        this.surveyCreatedAt = ZonedDateTime.now();
    }

    public void addShortAnswer(SurveyShortAnswer shortAnswer) {
        shortAnswer.setSurvey(this);
        this.shortAnswers.add(shortAnswer);
    }

    public void addLongAnswer(SurveyLongAnswer longAnswer) {
        longAnswer.setSurvey(this);
        this.longAnswers.add(longAnswer);
    }

    public void addSingleSelect(SurveySingleSelect singleSelect) {
        singleSelect.setSurvey(this);
        this.singleSelects.add(singleSelect);
    }

    public void addMultipleSelect(SurveyMultipleSelect multipleSelect) {
        multipleSelect.setSurvey(this);
        this.multipleSelects.add(multipleSelect);
    }

}
