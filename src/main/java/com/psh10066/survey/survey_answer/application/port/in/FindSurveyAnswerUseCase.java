package com.psh10066.survey.survey_answer.application.port.in;

import com.psh10066.survey.survey_answer.domain.SurveyAnswer;

import java.util.List;
import java.util.UUID;

public interface FindSurveyAnswerUseCase {

    List<SurveyAnswer> findAnswers(UUID surveyId);
}
