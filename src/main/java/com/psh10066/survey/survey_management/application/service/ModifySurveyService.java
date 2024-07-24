package com.psh10066.survey.survey_management.application.service;

import com.psh10066.survey.survey_management.application.port.in.ModifySurveyUseCase;
import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.application.port.out.FindSurveyPort;
import com.psh10066.survey.survey_management.application.port.out.ModifySurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifySurveyService implements ModifySurveyUseCase {

    private final ModifySurveyPort modifySurveyPort;
    private final FindSurveyPort findSurveyPort;

    @Transactional
    @Override
    public Survey.SurveyId modifySurvey(ModifySurveyCommand command) {
        Survey.SurveyId surveyId = new Survey.SurveyId(command.getId());
        Long latestVersion = findSurveyPort.getLatestVersion(surveyId);

        SurveyForm surveyForm = SurveyForm.update(
            latestVersion + 1,
            command.getName(),
            command.getDescription(),
            command.getQuestions().stream()
                .map(SurveyQuestionDto::toModel)
                .toList()
        );
        Survey survey = Survey.update(surveyId, surveyForm);
        return modifySurveyPort.modify(survey);
    }
}
