package com.innercircle.survey.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyUpdateRequestDto {
    @NotBlank(message = "질문 제목을 입력해 주세요.")
    private String title;

    private String description;

    private String questionType;

    private List<SurveyQuestionUpdateRequestDto> questions;
}