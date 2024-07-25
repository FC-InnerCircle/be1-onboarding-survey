package com.innercircle.survey.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SelectOption {

    private Long selectListId;

    private String selectListName;

    private SurveyItemOption selectOption;

}
