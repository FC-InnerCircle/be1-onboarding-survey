package com.psh10066.survey.survey_management.domain;

import org.springframework.util.CollectionUtils;

import java.util.List;

public record SurveyQuestion(
    String name,
    String description,
    SurveyQuestionType type,
    List<SurveySelectInput> selectInputs,
    boolean required
) {

    public SurveyQuestion(String name, String description, SurveyQuestionType type, List<SurveySelectInput> selectInputs, boolean required) {
        if (type.isSelectable()) {
            if (CollectionUtils.isEmpty(selectInputs)) {
                throw new IllegalArgumentException("Select inputs cannot be empty");
            }
            this.selectInputs = selectInputs;
        } else {
            this.selectInputs = null;
        }
        this.name = name;
        this.description = description;
        this.type = type;
        this.required = required;
    }
}
