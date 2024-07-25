package com.innercircle.surveycommon.dto.response

data class QuestionDto(
    val id: Int,
    val formId: Int,
    val formVersion: Int,
    val questionText: String,
    val questionType: String,
    val questionOrder: Int,
    val isRequired: Boolean,
    val additionalConfig: String?,
//    val responses: List<ResponseDto> = emptyList(),
    val questionOptions: List<QuestionOptionDto> = emptyList(),
)
