package com.example.survey.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}
