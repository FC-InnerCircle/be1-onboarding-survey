package com.psh10066.survey.survey_answer.domain;

import com.psh10066.survey.survey_management.domain.Survey;
import lombok.Getter;

import java.util.List;

@Getter
public class SurveyAnswer {

    private Long id;
    private Survey.SurveyId surveyId;
    private Long surveyVersion;
    private List<SurveyQuestionAnswer> answers;

    private SurveyAnswer(Survey.SurveyId surveyId, Long surveyVersion, List<SurveyQuestionAnswer> answers) {
        this.surveyId = surveyId;
        this.surveyVersion = surveyVersion;
        this.answers = answers;
    }

    public static SurveyAnswer create(Survey.SurveyId surveyId, Long surveyVersion, List<SurveyQuestionAnswer> answers) {
        return new SurveyAnswer(surveyId, surveyVersion, answers);
    }
}
