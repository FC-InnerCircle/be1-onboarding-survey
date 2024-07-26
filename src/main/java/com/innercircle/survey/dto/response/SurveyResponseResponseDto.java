package com.innercircle.survey.dto.response;

import com.innercircle.survey.model.Response;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponseResponseDto {
    private List<AnswerResponseDto> answers;

    public static SurveyResponseResponseDto fromResponseEntity(Response response) {
        return SurveyResponseResponseDto.builder()
                .answers(response.getAnswers().stream()
                        .map(AnswerResponseDto::fromAnswerEntity)
                        .toList())
                .build();
    }
}
