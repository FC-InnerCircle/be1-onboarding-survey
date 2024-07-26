package com.innercircle.surveycommon.dto.request

data class SubmissionRequest(
    val question_id: Int,
    val response_data: String?,
    val question_option_id: Int?,
)
