package com.innercircle.survey.domain.survey;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.innercircle.survey.domain.survey.command.SurveyCommand.Create.GetSurveyItem;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyItem {


    private Long surveyItemId;

    private String surveyItemName;

    private String surveyItemDescription;

    private boolean itemRequired;

    private Survey survey;

    private ItemInputFormat inputFormat;

    private LocalDateTime createdAt;

    private List<SelectOption> selectOptions;

    public void setSelectOptions(List<SelectOption> selectOptions) {
        this.selectOptions = selectOptions;
    }

    public enum ItemInputFormat {
        SHORT_ANSWER,
        LONG_ANSWER,
        SINGLE_SELECTION,
        MULTIPLE_SELECTION
    }

//    public void setSurveyItemOption(SurveyItemOption surveyItemOption) {
//        this.surveyItemOption = surveyItemOption;
//    }

    public static SurveyItem toSurveyItemDomain(GetSurveyItem surveyItem, Survey survey) {
        return SurveyItem.builder()
                .survey(survey)
                .surveyItemName(surveyItem.surveyItemName())
                .surveyItemDescription(surveyItem.surveyItemDescription())
                .itemRequired(surveyItem.itemRequired())
                .inputFormat(surveyItem.inputFormat())
//                .selectOptions(surveyItem.selectOptions() != null ? surveyItem.selectOptions().stream().map(getSelectOption
//                        -> SelectOption.builder().selectItemName(getSelectOption.name()).build()).toList()
//                        : null)
                .build();
    }
}
