package com.innercircle.survey.domain.survey.command;

import com.innercircle.survey.domain.survey.SurveyItem;
import lombok.Builder;

import java.util.List;

public class SurveyCommand {

    @Builder(toBuilder = true)
    public record Create(
            String surveyName,
            String surveyDescription,
            List<GetSurveyItem> surveyItems
    ) {
        @Builder(toBuilder = true)
        public record GetSurveyItem(
                String surveyItemName,
                String surveyItemDescription,
                SurveyItem.ItemInputFormat inputFormat,
                Boolean itemRequired,
                List<GetSelectOption> selectOptions
        ) {
            @Builder(toBuilder = true)
            public record GetSelectOption(String name) {
            }
        }
    }
}
