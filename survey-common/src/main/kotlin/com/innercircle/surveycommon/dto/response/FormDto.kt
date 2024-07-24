package com.innercircle.surveycommon.dto.response

data class FormDto(
    val id: Long,
    val title: String,
    val description: String,
    val created_at: String,
    val updated_at: String,
    val is_active: Boolean,
    val version: Int,
)
