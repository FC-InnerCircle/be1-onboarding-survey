package com.example.survey.adapter.dto;

import java.util.List;

import com.example.survey.domain.model.InputType;

public class SurveyItemDto {
    private Long id;
    private String name;
    private String description;
    private InputType inputType;
    private boolean required;
    private List<String> options;
}
