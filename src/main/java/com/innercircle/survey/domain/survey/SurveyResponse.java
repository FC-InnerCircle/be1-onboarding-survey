package com.innercircle.survey.domain.survey;

import com.innercircle.survey.domain.survey.command.SurveyResponseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.innercircle.survey.domain.survey.SurveyItem.ItemInputFormat.LONG_ANSWER;
import static com.innercircle.survey.domain.survey.SurveyItem.ItemInputFormat.SHORT_ANSWER;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyResponse {

    private Long surveyResponseId;

    private Survey survey;

    private Long surveyId;

    private Long surveyItemId;

    private String responseShortValue;

    private String responseLongValue;

    private SurveyItem surveyItem;

    private List<SurveyResponseDetail> surveyResponseDetails;

    private SurveyItem.ItemInputFormat inputFormat;

    private LocalDateTime createdAt;

    public static List<SurveyResponse> toSurveyResponseDomains(SurveyResponseCommand.Create command) {
        return command.surveyResponses()
                .stream()
                .map(getSurveyResponses ->
                        SurveyResponse.builder()
                                .surveyId(command.surveyId())
                                .surveyItemId(getSurveyResponses.surveyItemId())
                                .inputFormat(getSurveyResponses.inputFormat())
                                .responseShortValue(getSurveyResponses.inputFormat() == SHORT_ANSWER ?
                                        getSurveyResponses.answerText() : null)
                                .responseLongValue(getSurveyResponses.inputFormat() == LONG_ANSWER ?
                                        getSurveyResponses.answerText() : null)
                                .build()
                ).toList();
    }

    public void setResponseDetail(List<SurveyResponseDetail> saveResponseDetails) {

        this.surveyResponseDetails = saveResponseDetails;

    }
}
