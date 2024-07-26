package com.inner_circle.survey.dto.request;

import java.util.List;

public record RespondentRequest(
    String name,
    List<UserResponseRequest> responses
) {
}
