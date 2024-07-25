package com.innercircle.survey.domain.survey;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.question.SurveyLongAnswer;
import com.innercircle.survey.domain.survey.question.SurveyMultipleSelect;
import com.innercircle.survey.domain.survey.question.SurveyShortAnswer;
import com.innercircle.survey.domain.survey.question.SurveySingleSelect;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "surveys")
public class Survey extends AbstractEntity {

    private static final String SURVEY_PREFIX = "sv_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surveyToken;
    private Long version;

    @OneToMany(mappedBy = "survey")
    private List<SurveyShortAnswer> shortAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveyLongAnswer> longAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveySingleSelect> singleSelects = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveyMultipleSelect> multipleSelects = new ArrayList<>();

}
