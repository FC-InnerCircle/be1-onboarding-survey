package com.innercircle.survey.dto.response;

import com.innercircle.survey.model.Response;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponseResponseDtos {
    private List<SurveyResponseResponseDto> responses;

    public static SurveyResponseResponseDtos fromResponseEntities(List<Response> responses) {
        return SurveyResponseResponseDtos.builder()
                .responses(responses.stream()
                        .map(SurveyResponseResponseDto::fromResponseEntity)
                        .toList())
                .build();
    }
}
