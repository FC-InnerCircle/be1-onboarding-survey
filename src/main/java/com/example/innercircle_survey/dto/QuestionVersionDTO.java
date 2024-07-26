package com.example.innercircle_survey.dto;

import com.example.innercircle_survey.entity.QuestionVersion;
import com.example.innercircle_survey.enumeration.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * - [설문 받을 항목]은 [항목 이름], [항목 설명], [항목 입력 형태], [항목 필수 여부]의 구성으로 이루어져있습니다.
 * */

@Getter
@Setter
@Builder
public class QuestionVersionDTO {

    private Integer number;

    private String title;

    private String description;

    private QuestionType type;

    private Boolean required;

    private Boolean valid;

    private Boolean updated;  // false 일 경우 버전업 없이 그대로 사용

    private List<String> options;  // 단일, 다중 리스트의 경우 옵션 선택

    public static QuestionVersionDTO create(QuestionVersion entity) {
        return QuestionVersionDTO.builder()
                .number(entity.getNumber())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .type(entity.getType())
                .build();
    }
}
