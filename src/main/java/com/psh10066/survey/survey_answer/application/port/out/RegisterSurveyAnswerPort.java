package com.psh10066.survey.survey_answer.application.port.out;

import com.psh10066.survey.survey_answer.domain.SurveyAnswer;

public interface RegisterSurveyAnswerPort {

    Long register(SurveyAnswer surveyAnswer);
}
