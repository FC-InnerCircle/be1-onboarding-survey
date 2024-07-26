package com.innercircle.survey.dto.response;
import com.innercircle.survey.model.Survey;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponseDto {
    private String title;

    private String description;

    private List<SurveyQuestionResponseDto> questions;

    public static SurveyResponseDto fromEntity(Survey survey) {
        return SurveyResponseDto.builder()
                .title(survey.getTitle())
                .description(survey.getDescription())
                .questions(
                        SurveyQuestionResponseDtos.fromEntity(survey.getQuestions())
                                .getQuestions()
                )
                .build();
    }
}
