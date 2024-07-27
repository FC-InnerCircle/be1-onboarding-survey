package com.innercircle.survey.interfaces.doc;

import com.innercircle.survey.common.response.CommonResponse;
import com.innercircle.survey.interfaces.survey.SurveyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Surveys Admin", description = "설문조사 도메인 API (Admin)")
public interface SurveyAdminControllerDoc {

    @Operation(summary = "설문조사 생성", description = "설문조사를 생성합니다.")
    CommonResponse registerSurvey(SurveyDto.RegisterRequest request);

    @Operation(summary = "설문조사 수정", description = "설문조사를 수정합니다.")
    CommonResponse updateSurvey(SurveyDto.UpdateRequest request);

    @Operation(summary = "설문조사 결과", description = "설문조사 결과를 확인합니다.")
    CommonResponse surveyResponseResult(SurveyDto.ResponseResultRequest request);
}
