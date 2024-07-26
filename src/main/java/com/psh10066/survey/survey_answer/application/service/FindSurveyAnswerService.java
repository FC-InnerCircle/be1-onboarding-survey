package com.psh10066.survey.survey_answer.application.service;

import com.psh10066.survey.survey_answer.application.port.in.FindSurveyAnswerUseCase;
import com.psh10066.survey.survey_answer.application.port.out.FindSurveyAnswerPort;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindSurveyAnswerService implements FindSurveyAnswerUseCase {

    private final FindSurveyAnswerPort findSurveyAnswerPort;

    @Override
    public List<SurveyAnswer> findAnswers(UUID surveyId) {
        return findSurveyAnswerPort.findBySurveyId(surveyId);
    }
}
