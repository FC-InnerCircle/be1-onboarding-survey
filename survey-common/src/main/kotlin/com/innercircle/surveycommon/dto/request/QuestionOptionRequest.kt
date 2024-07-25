package com.innercircle.surveycommon.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class QuestionOptionRequest(
    @JsonProperty("option_text")
    val optionText: String,
    @JsonProperty("option_order")
    val optionOrder: Int,
)
