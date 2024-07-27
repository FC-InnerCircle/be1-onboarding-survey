package com.innercircle.survey.domain.survey;

import com.innercircle.survey.common.util.TokenGenerator;
import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.question.*;
import com.innercircle.survey.domain.survey.response.SurveyResponse;
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
    private List<SurveyQuestion> surveyQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "survey")
    private List<SurveyResponse> surveyResponses = new ArrayList<>();
}
