@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveyapi.api

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.request.FormSubmissionRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse
import com.innercircle.surveycommon.dto.response.FormSubmissionResponse
import com.innercircle.surveycommon.dto.response.FormsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

// RESTful API를 정의하는 인터페이스입니다.
// API의 구현체는 SurveyApiControllerImpl 클래스에 정의되어 있습니다.
// API를 설명하는 Swagger 문서를 별도 생성 및 관리하기 위해 구현체 별도 분리합니다.
@Tag(name = "Surveys", description = "Survey operations")
@RequestMapping("/v1/api")
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
    @GetMapping("/forms")
    fun getForms(
        @Parameter(description = "활성화된 폼만 필터링", required = false)
        @RequestParam(required = false) active: Boolean?,
    ): FormsResponse

    // TODO: 온보딩 필수 API 4개 선행 추가 및 개발 진행 24.07.25
    @Operation(
        summary = "폼 생성",
        description = "새로운 폼을 생성합니다.",
        requestBody =
            io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CreateFormRequest::class),
                        examples = [
                            ExampleObject(
                                name = "예시 요청값",
                                value = """
                        {
                          "title": "신규 사용자 설문",
                          "description": "신규 사용자 경험 개선을 위한 설문조사",
                          "is_active": true,
                          "questions": [
                            {
                              "question_text": "전반적인 서비스 만족도는 어떠신가요?",
                              "question_type": "rating",
                              "question_order": 1,
                              "is_required": true,
                              "additional_config": {
                                "min": 1,
                                "max": 5
                              }
                            },
                            {
                              "question_text": "우리 서비스를 어떻게 알게 되셨나요?",
                              "question_type": "single_choice",
                              "question_order": 2,
                              "is_required": true,
                              "question_options": [
                                {"option_text": "인터넷 검색", "option_order": 1},
                                {"option_text": "친구 추천", "option_order": 2},
                                {"option_text": "광고", "option_order": 3},
                                {"option_text": "기타", "option_order": 4}
                              ]
                            }
                          ]
                        }
                        """,
                            ),
                        ],
                    ),
                ],
            ),
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "성공적으로 생성되었습니다.",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CreateFormResponse::class),
                        examples = [
                            ExampleObject(
                                name = "예시 응답값",
                                value = """
                            {
                              "id": 2,
                              "title": "신규 사용자 설문",
                              "description": "신규 사용자 경험 개선을 위한 설문조사",
                              "created_at": "2024-07-23T11:00:00Z",
                              "updated_at": "2024-07-23T11:00:00Z",
                              "is_active": true,
                              "version": 1,
                              "questions": [
                                {
                                  "id": 1,
                                  "question_text": "전반적인 서비스 만족도는 어떠신가요?",
                                  "question_type": "rating",
                                  "question_order": 1,
                                  "is_required": true,
                                  "additional_config": {
                                    "min": 1,
                                    "max": 5
                                  }
                                },
                                {
                                  "id": 2,
                                  "question_text": "우리 서비스를 어떻게 알게 되셨나요?",
                                  "question_type": "single_choice",
                                  "question_order": 2,
                                  "is_required": true,
                                  "question_options": [
                                    {"id": 1, "option_text": "인터넷 검색", "order": 1},
                                    {"id": 2, "option_text": "친구 추천", "order": 2},
                                    {"id": 3, "option_text": "광고", "order": 3},
                                    {"id": 4, "option_text": "기타", "order": 4}
                                  ]
                                }
                              ]
                            }
                            """,
                            ),
                        ],
                    ),
                ],
            ),
        ],
    )
    @PostMapping("/forms")
    fun createForm(
        @RequestBody request: CreateFormRequest,
    ): CreateFormResponse

    @Operation(
        summary = "폼 제출",
        description = "사용자가 작성한 폼을 제출합니다.",
        parameters = [
            Parameter(
                name = "forms_id",
                description = "제출할 폼의 ID",
                required = true,
                schema = Schema(type = "integer", format = "int64"),
                example = "1",
            ),
        ],
        requestBody =
            io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = FormSubmissionRequest::class),
                        examples = [
                            ExampleObject(
                                name = "예시 요청값",
                                value = """
                        {
                          "respondent_info": "anonymous",
                          "responses": [
                            {
                              "question_id": 1,
                              "response_data": "4"
                            },
                            {
                              "question_id": 2,
                              "question_option_id": 2
                            }
                          ]
                        }
                        """,
                            ),
                        ],
                    ),
                ],
            ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "폼이 성공적으로 제출되었습니다.",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = FormSubmissionResponse::class),
                        examples = [
                            ExampleObject(
                                name = "예시 응답값",
                                value = """
                            {
                              "id": 1,
                              "form_id": 1,
                              "form_version": 1,
                              "submitted_at": "2024-07-23T14:30:00Z",
                              "respondent_info": "anonymous"
                            }
                            """,
                            ),
                        ],
                    ),
                ],
            ),
//            ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
//            ApiResponse(responseCode = "404", description = "요청한 폼을 찾을 수 없습니다."),
//            ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
        ],
    )
    @PostMapping("/forms/{forms_id}/submissions")
    fun submitForm(
        @PathVariable forms_id: Long,
        @RequestBody submission: FormSubmissionRequest,
    ): FormSubmissionResponse
}
