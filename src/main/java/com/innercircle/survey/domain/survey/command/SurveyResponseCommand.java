package com.innercircle.survey.domain.survey.command;

import com.innercircle.survey.domain.survey.SurveyItem;
import lombok.Builder;

import java.util.List;

public class SurveyResponseCommand {

    @Builder(toBuilder = true)
    public record Create(
            Long surveyId,
            List<GetSurveyResponses> surveyResponses
    ) {
        @Builder(toBuilder = true)
        public record GetSurveyResponses(
                Long surveyItemId,
                SurveyItem.ItemInputFormat inputFormat,
                String answerText,
                List<GetSurveyResponses.GetAnswerOption> answerOptions
        ) {
            @Builder(toBuilder = true)
            public record GetAnswerOption(String name) {
            }
        }
    }
}
