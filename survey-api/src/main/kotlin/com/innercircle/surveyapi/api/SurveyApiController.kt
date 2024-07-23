package com.innercircle.surveyapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// RESTful API를 정의하는 인터페이스입니다.
// API의 구현체는 SurveyApiControllerImpl 클래스에 정의되어 있습니다.
// API를 설명하는 Swagger 문서를 별도 생성 및 관리하기 위해 구현체 별도 분리합니다.
@Component
@Tag(name = "Surveys", description = "Survey operations")
@RestController
@RequestMapping("/api/surveys")
interface SurveyApiController {
    // 설문조사 생성 API
    @Operation(summary = "새로운 설문조사를 생성합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공적으로 생성되었습니다."),
            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다."),
        ],
    )
    @PostMapping(value = ["/create"])
    fun createSurvey(): String

    // 설문조사 수정 API
    @Operation(summary = "설문조사를 수정합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공적으로 수정되었습니다."),
            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            ApiResponse(responseCode = "404", description = "설문조사를 찾을 수 없습니다."),
            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다."),
        ],
    )
    @PostMapping(value = ["/update"])
    fun updateSurvey(): String

    // 설문조사 응답제출 API
    @Operation(summary = "설문조사 응답을 제출합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공적으로 제출되었습니다."),
            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다."),
        ],
    )
    @PostMapping(value = ["/submit"])
    fun submitSurveyResponse(): String

    // 설문조사 응답조회 API
    @Operation(summary = "설문조사 응답을 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            ApiResponse(responseCode = "404", description = "응답을 찾을 수 없습니다."),
            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다."),
        ],
    )
    @GetMapping(value = ["/response"])
    fun getSurveyResponse(): String
}
