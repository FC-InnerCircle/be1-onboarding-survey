package com.innercircle.survey.interfaces.servey;

import com.innercircle.survey.common.response.CommonResponse;
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


    @PostMapping
    public CommonResponse createSurvey(@RequestBody @Valid SurveyDto.RegisterRequest request) {
        return CommonResponse.success();
    }

    @PutMapping
    public CommonResponse updateSurvey(@RequestBody @Valid SurveyDto.RegisterRequest request) {
        return CommonResponse.success();
    }

    public CommonResponse surveyResponseResult(SurveyDto.ResponseResultRequest request) {
        return null;
    }
}
