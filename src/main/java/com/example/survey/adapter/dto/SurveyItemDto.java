package com.example.survey.adapter.dto;

import java.util.List;

import com.example.survey.domain.model.InputType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SurveyItemDto {
    private Long id;
    @NotEmpty(message = "Item name is required")
    private String name;
    private String description;
    @NotNull(message = "Item input type is required")
    private InputType inputType;
    private boolean required;
    private List<String> options;
}
