package com.innercircle.surveycommon.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class QuestionRequest(
    @JsonProperty("question_text")
    val questionText: String,
    @JsonProperty("question_type")
    val questionType: String,
    @JsonProperty("question_order")
    val questionOrder: Int,
    @JsonProperty("is_required")
    val isRequired: Boolean,
    @JsonProperty("additional_config")
    val additionalConfig: Map<String, Any>? = null,
    @JsonProperty("question_options")
    val questionOptions: List<QuestionOptionRequest>? = null,
)
