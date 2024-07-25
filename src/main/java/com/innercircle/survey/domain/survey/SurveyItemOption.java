package com.innercircle.survey.domain.survey;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyItemOption {

    private Long optionId;

    private SurveyItem surveyItem;

    @Enumerated(EnumType.STRING)
    private ItemInputFormat inputFormat;

    public enum ItemInputFormat {
        SHORT_ANSWER,
        LONG_ANSWER,
        SINGLE_SELECTION,
        MULTIPLE_SELECTION
    }

    private List<SelectOption> selectOptions;
}
