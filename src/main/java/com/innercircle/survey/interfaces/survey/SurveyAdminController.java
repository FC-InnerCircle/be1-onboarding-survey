package com.innercircle.survey.interfaces.survey;

import com.innercircle.survey.application.survey.SurveyAdminFacade;
import com.innercircle.survey.common.response.CommonResponse;
import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.interfaces.doc.SurveyAdminControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/surveys")
public class SurveyAdminController implements SurveyAdminControllerDoc {

    private final SurveyAdminFacade surveyAdminFacade;


    @PostMapping
    public CommonResponse registerSurvey(@RequestBody @Valid SurveyDto.RegisterRequest request) {

        var survey = request.toEntity();
        var surveyToken = surveyAdminFacade.registerSurvey(survey);
        return CommonResponse.success(SurveyDto.RegisterResponse.of(surveyToken));
    }

    @PutMapping
    public CommonResponse updateSurvey(@RequestBody @Valid SurveyDto.UpdateRequest request) {
        var updateSurvey = request.toEntity();
        surveyAdminFacade.updateSurvey(updateSurvey);
        return CommonResponse.success("OK");
    }

    public CommonResponse surveyResponseResult(SurveyDto.ResponseResultRequest request) {
        return null;
    }
}
