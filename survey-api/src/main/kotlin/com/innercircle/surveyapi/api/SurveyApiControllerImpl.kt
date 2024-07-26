package com.innercircle.surveyapi.api

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.request.FormSubmissionRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse
import com.innercircle.surveycommon.dto.response.FormDto
import com.innercircle.surveycommon.dto.response.FormSubmissionResponse
import com.innercircle.surveycommon.dto.response.FormsResponse
import com.innercircle.surveydomain.service.SurveyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Component
@RestController
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

    override fun submitForm(
        @PathVariable forms_id: Long,
        @RequestBody request: FormSubmissionRequest,
    ): FormSubmissionResponse {
        // TODO: Implement the logic to submit the form
        surveyService.submitForm(forms_id, request)

        val response =
            FormSubmissionResponse(
                id = 1, // 실제로는 생성된 ID를 사용해야 합니다
                form_id = forms_id,
                form_version = 1, // 실제 버전 정보를 사용해야 합니다
                submitted_at = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                respondent_info = request.respondent_info,
            )
        return response
    }
}
