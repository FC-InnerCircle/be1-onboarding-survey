package com.psh10066.survey.survey_management.application.service;

import com.psh10066.survey.survey_management.application.port.in.RegisterSurveyUseCase;
import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.application.port.out.RegisterSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterSurveyService implements RegisterSurveyUseCase {

    private final RegisterSurveyPort registerSurveyPort;

    @Transactional
    @Override
    public Survey.SurveyId registerSurvey(RegisterSurveyCommand command) {
        SurveyForm surveyForm = SurveyForm.create(
            command.getName(),
            command.getDescription(),
            command.getQuestions().stream()
                .map(SurveyQuestionDto::toModel)
                .toList()
        );
        Survey survey = Survey.create(surveyForm);
        return registerSurveyPort.register(survey);
    }
}
