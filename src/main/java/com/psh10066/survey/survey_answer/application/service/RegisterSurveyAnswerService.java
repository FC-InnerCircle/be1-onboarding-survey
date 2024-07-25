package com.psh10066.survey.survey_answer.application.service;

import com.psh10066.survey.survey_answer.application.port.in.RegisterSurveyAnswerUseCase;
import com.psh10066.survey.survey_answer.application.port.in.command.RegisterSurveyAnswerCommand;
import com.psh10066.survey.survey_answer.application.port.in.command.dto.SurveyQuestionAnswerCommandDto;
import com.psh10066.survey.survey_answer.application.port.out.RegisterSurveyAnswerPort;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import com.psh10066.survey.survey_answer.domain.SurveyAnswerValidator;
import com.psh10066.survey.survey_management.application.port.out.FindSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterSurveyAnswerService implements RegisterSurveyAnswerUseCase {

    private final FindSurveyPort findSurveyPort;
    private final RegisterSurveyAnswerPort registerSurveyAnswerPort;

    @Transactional
    @Override
    public Long register(RegisterSurveyAnswerCommand command) {
        Survey survey = findSurveyPort.getSurvey(
            new Survey.SurveyId(command.getSurveyId()),
            command.getSurveyVersion()
        );
        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            new Survey.SurveyId(command.getSurveyId()),
            command.getSurveyVersion(),
            command.getAnswers().stream()
                .map(SurveyQuestionAnswerCommandDto::toModel)
                .toList()
        );

        SurveyAnswerValidator.verify(survey, surveyAnswer);

        return registerSurveyAnswerPort.register(surveyAnswer);
    }
}
