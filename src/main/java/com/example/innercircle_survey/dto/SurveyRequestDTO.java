package com.example.innercircle_survey.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * - 요청 값에는 [설문조사 이름], [설문조사 설명], [설문 받을 항목]이 포함됩니다.
 */
public class SurveyRequestDTO {

    // 서베이 제목
    @NotBlank
    private String title;

    // 서베이 상세 설명
    @NotEmpty
    private String description;

    // 응답 항목
    @NotEmpty
    private List<QuestionRequestDTO> questions;
}
