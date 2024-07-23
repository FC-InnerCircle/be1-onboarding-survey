package com.psh10066.survey.survey_management.adapter.in.web;

import com.psh10066.survey.survey_management.adapter.in.web.request.RegisterSurveyRequest;
import com.psh10066.survey.survey_management.adapter.in.web.response.RegisterSurveyResponse;
import com.psh10066.survey.survey_management.application.port.in.RegisterSurveyUseCase;
import com.psh10066.survey.survey_management.domain.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagementSurveyController {

    private final RegisterSurveyUseCase registerSurveyUseCase;

    @PostMapping(value = "/survey/register")
    public RegisterSurveyResponse registerSurvey(@RequestBody RegisterSurveyRequest request) {
        Survey.SurveyId surveyId = registerSurveyUseCase.registerSurvey(request.toCommand());
        return new RegisterSurveyResponse(surveyId.getValue());
    }
}
