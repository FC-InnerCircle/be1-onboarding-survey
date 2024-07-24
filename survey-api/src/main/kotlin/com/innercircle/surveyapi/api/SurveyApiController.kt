@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveyapi.api

import com.innercircle.surveycommon.dto.response.FormsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

// RESTful API를 정의하는 인터페이스입니다.
// API의 구현체는 SurveyApiControllerImpl 클래스에 정의되어 있습니다.
// API를 설명하는 Swagger 문서를 별도 생성 및 관리하기 위해 구현체 별도 분리합니다.
@Component
@Tag(name = "Surveys", description = "Survey operations")
@RestController
@RequestMapping("/api/surveys")
interface SurveyApiController {
    @Operation(
        summary = "폼 목록 조회",
        description = "사용 가능한 모든 폼의 목록을 반환합니다.",
        parameters = [
            Parameter(
                name = "active",
                description = "활성화된 폼만 필터링",
                required = false,
                schema = Schema(type = "boolean"),
                example = "true",
            ),
        ],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "성공적으로 조회되었습니다.",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = FormsResponse::class),
                        examples = [
                            ExampleObject(
                                name = "성공 응답",
                                value = """
                                {
                                  "forms": [
                                    {
                                      "id": 1,
                                      "title": "고객 만족도 조사",
                                      "description": "서비스 품질 개선을 위한 설문조사",
                                      "created_at": "2024-07-23T10:00:00Z",
                                      "updated_at": "2024-07-23T10:00:00Z",
                                      "is_active": true,
                                      "version": 1
                                    }
                                  ]
                                }
                                """,
                            ),
                        ],
                    ),
                ],
            ),
//            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
//            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다."),
        ],
    )
    @GetMapping("/v1/api/forms")
    fun getForms(
        @Parameter(description = "활성화된 폼만 필터링", required = false)
        @RequestParam(required = false) active: Boolean?,
    ): FormsResponse

    // TODO: 온보딩 필수 API 4개 선행 추가 및 개발 진행 24.07.25
}
