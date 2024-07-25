package com.innercircle.survey.application.survey;

import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.SurveyAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyAdminFacade {

    private final SurveyAdminService surveyAdminService;

    public String registerSurvey(Survey survey) {
        var surveyToken = surveyAdminService.registerSurvey(survey);
        return surveyToken;
    }

    public void updateSurvey(Survey updateSurvey) {

    }
}
