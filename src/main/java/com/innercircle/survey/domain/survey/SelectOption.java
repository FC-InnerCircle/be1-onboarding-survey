package com.innercircle.survey.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.innercircle.survey.domain.survey.command.SurveyCommand.Create.GetSurveyItem.GetSelectOption;


@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SelectOption {

    private Long selectOptionId;

    private String selectItemName;

    private SurveyItem surveyItem;

    public static List<SelectOption> toSelectOptionDomains(List<GetSelectOption> selectOptions,
                                                           SurveyItem saveSurveyItem) {
        return selectOptions
                .stream()
                .map(getSelectOption ->
                        SelectOption.builder()
                                .surveyItem(saveSurveyItem)
                                .selectItemName(getSelectOption.name())
                                .build()).toList();
    }
}
