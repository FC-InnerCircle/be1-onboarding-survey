package com.inner_circle.survey.dto.request;

import java.util.List;

public record UserResponseRequest(
    Long id,
    String answer,
    List<Long> options
) {
}
