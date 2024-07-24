package com.psh10066.survey.survey_management.adapter.in.web;

import com.psh10066.survey.survey_management.adapter.in.web.request.ModifySurveyRequest;
import com.psh10066.survey.survey_management.adapter.in.web.request.RegisterSurveyRequest;
import com.psh10066.survey.survey_management.adapter.in.web.response.ModifySurveyResponse;
import com.psh10066.survey.survey_management.adapter.in.web.response.RegisterSurveyResponse;
import com.psh10066.survey.survey_management.application.port.in.ModifySurveyUseCase;
import com.psh10066.survey.survey_management.application.port.in.RegisterSurveyUseCase;
import com.psh10066.survey.survey_management.domain.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagementSurveyController {

    private final RegisterSurveyUseCase registerSurveyUseCase;
    private final ModifySurveyUseCase modifySurveyUseCase;

    @PostMapping(value = "/survey/register")
    public RegisterSurveyResponse registerSurvey(@RequestBody RegisterSurveyRequest request) {
        Survey.SurveyId surveyId = registerSurveyUseCase.registerSurvey(request.toCommand());
        return new RegisterSurveyResponse(surveyId.value());
    }

    @PutMapping(value = "/survey/modify")
    public ModifySurveyResponse modifySurvey(@RequestBody ModifySurveyRequest request) {
        Survey.SurveyId surveyId = modifySurveyUseCase.modifySurvey(request.toCommand());
        return new ModifySurveyResponse(surveyId.value());
    }
}
