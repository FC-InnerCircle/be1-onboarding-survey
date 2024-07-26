package com.psh10066.survey.survey_answer.application.port.out;

import com.psh10066.survey.survey_answer.domain.SurveyAnswer;

import java.util.List;
import java.util.UUID;

public interface FindSurveyAnswerPort {

    List<SurveyAnswer> findBySurveyId(UUID surveyId);
}
