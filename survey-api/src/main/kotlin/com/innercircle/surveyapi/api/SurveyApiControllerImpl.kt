package com.innercircle.surveyapi.api

import com.innercircle.surveycommon.dto.response.FormDto
import com.innercircle.surveycommon.dto.response.FormsResponse

class SurveyApiControllerImpl : SurveyApiController {
    override fun getForms(active: Boolean?): FormsResponse =
        FormsResponse(
            forms =
                listOf(
                    FormDto(
                        id = 1,
                        title = "고객 만족도 조사",
                        description = "서비스 품질 개선을 위한 설문조사",
                        created_at = "2024-07-23T10:00:00Z",
                        updated_at = "2024-07-23T10:00:00Z",
                        is_active = true,
                        version = 1,
                    ),
                ),
        )
}
