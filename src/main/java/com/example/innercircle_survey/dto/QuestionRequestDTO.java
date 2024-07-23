package com.example.innercircle_survey.dto;

import com.example.innercircle_survey.enumeration.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * - [설문 받을 항목]은 [항목 이름], [항목 설명], [항목 입력 형태], [항목 필수 여부]의 구성으로 이루어져있습니다.
 * */
public class QuestionRequestDTO {

    @NotEmpty
    private Integer number;

    @NotBlank
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private QuestionType type;

    @NotEmpty
    private Boolean required;
}
