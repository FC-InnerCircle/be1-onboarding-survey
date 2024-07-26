package com.example.innercircle_survey.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SurveyVersionDTO {

    private Long id;

    private Integer versionNumber;

    // 서베이 제목
    private String title;

    // 서베이 상세 설명
    private String description;

    // 응답 항목
    private List<QuestionVersionDTO> questions;
}
