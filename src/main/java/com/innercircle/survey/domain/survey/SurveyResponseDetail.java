package com.innercircle.survey.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.innercircle.survey.domain.survey.command.SurveyResponseCommand.Create.GetSurveyResponses;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyResponseDetail {

    private Long responseDetailId;

    private SurveyResponse response;

    private String responseValue;

    private LocalDateTime createdAt;

    public static List<SurveyResponseDetail> toResponseDetailDomains(List<GetSurveyResponses.GetAnswerOption> getAnswerOptions,
                                                                     SurveyResponse response) {
        return getAnswerOptions.stream()
                .map(answerOption ->
                        SurveyResponseDetail.builder()
                                .response(response)
                                .responseValue(answerOption.name())
                                .build())
                .toList();

    }
}
