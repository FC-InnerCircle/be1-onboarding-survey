package com.innercircle.surveydomain.service

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse

interface SurveyService {
    fun createForm(request: CreateFormRequest): CreateFormResponse
}
