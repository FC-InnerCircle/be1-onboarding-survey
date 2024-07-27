package com.example.survey.adapter.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SurveyDto {
    private Long surveyId;
    @NotEmpty(message = "Survey name is required")
    private String name;
    private String description;
    @NotNull(message = "Survey items are required")
    @NotEmpty(message = "Survey must have at least one item")
    private List<SurveyItemDto> items;
}
