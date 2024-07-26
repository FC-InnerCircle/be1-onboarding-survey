package com.inner_circle.survey.dto.response;

import java.util.List;

public record RespondentResponse(
    Long id,
    String name,
    List<UserResponseResponse> responses
) {
}
