package com.inner_circle.survey.dto.response;

import java.util.List;

public record UserResponseResponse(
    Long id,
    String title,
    String description,
    String answer,
    List<ResponseOptionResponse> options
) {
}
