package com.innercircle.surveycommon.dto.response

data class CreateFormResponse(
    val form: FormDto,
    val questions: List<QuestionDto>,
)
