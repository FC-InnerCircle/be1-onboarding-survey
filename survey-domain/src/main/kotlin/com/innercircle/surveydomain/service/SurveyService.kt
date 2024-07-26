package com.innercircle.surveydomain.service

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.request.FormSubmissionRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse
import com.innercircle.surveycommon.dto.response.FormSubmissionResponse

interface SurveyService {
    fun createForm(request: CreateFormRequest): CreateFormResponse

    fun submitForm(
        forms_id: Long,
        request: FormSubmissionRequest,
    ): FormSubmissionResponse
}
