package com.innercircle.surveyapi.api

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse
import com.innercircle.surveycommon.dto.response.FormDto
import com.innercircle.surveycommon.dto.response.FormsResponse
import com.innercircle.surveydomain.service.SurveyService
import org.springframework.beans.factory.annotation.Autowired

class SurveyApiControllerImpl : SurveyApiController {
    @Autowired
    private lateinit var surveyService: SurveyService

    override fun getForms(active: Boolean?): FormsResponse =
        FormsResponse(
            forms =
                listOf(
                    FormDto(
                        id = 1,
                        title = "고객 만족도 조사",
                        description = "서비스 품질 개선을 위한 설문조사",
                        createdAt = "2024-07-23T10:00:00Z",
                        updatedAt = "2024-07-23T10:00:00Z",
                        isActive = true,
                        version = 1,
                    ),
                ),
        )

    // CreateFormResponse
    override fun createForm(request: CreateFormRequest): CreateFormResponse = surveyService.createForm(request)
}
