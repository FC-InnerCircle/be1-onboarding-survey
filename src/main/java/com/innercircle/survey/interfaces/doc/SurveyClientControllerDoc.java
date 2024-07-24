package com.innercircle.survey.interfaces.doc;

import com.innercircle.survey.common.response.CommonResponse;
import com.innercircle.survey.interfaces.servey.SurveyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Survey Client", description = "설문조사 도메인 API (Client)")
public interface SurveyClientControllerDoc {

    @Operation(summary = "설문조사 데이터 가져오기", description = "설문조사 데이터를 가져옵니다.")
    CommonResponse getSurvey(String surveyToken);

    @Operation(summary = "설문조사 데이터 제출", description = "설문조사를 제출합니다.")
    CommonResponse submitSurvey(HttpServletRequest request, SurveyDto.SurveySubmitRequest surveySubmitRequest);
}
