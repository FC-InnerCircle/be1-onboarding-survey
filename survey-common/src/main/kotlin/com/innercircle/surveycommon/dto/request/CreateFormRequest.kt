package com.innercircle.surveycommon.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateFormRequest(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("is_active")
    val isActive: Boolean? = false,
    @JsonProperty("questions")
    val questions: List<QuestionRequest>,
)
