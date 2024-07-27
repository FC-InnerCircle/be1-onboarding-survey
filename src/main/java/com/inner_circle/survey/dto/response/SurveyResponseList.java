package com.inner_circle.survey.dto.response;

import java.util.List;

public record SurveyResponseList(
    List<SurveyResponse> surveys
) {
}
