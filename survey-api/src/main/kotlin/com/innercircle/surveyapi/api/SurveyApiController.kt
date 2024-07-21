package com.innercircle.surveyapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping

// RESTful API를 정의하는 인터페이스입니다.
// API의 구현체는 SurveyApiControllerImpl 클래스에 정의되어 있습니다.
// API를 설명하는 Swagger 문서를 별도 생성 및 관리하기 위해 구현체 별도 분리합니다.
interface SurveyApiController {
    @Operation(summary = "모든 설문조사 목록을 가져옵니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다.")
    @GetMapping(value = ["", "/"])
    fun getAllSurveys(): List<String>
}