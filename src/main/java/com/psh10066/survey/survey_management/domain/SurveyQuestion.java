package com.psh10066.survey.survey_management.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public record SurveyQuestion(
    Integer id,
    String name,
    String description,
    SurveyQuestionType type,
    List<SurveySelectInput> selectInputs,
    boolean required
) {

    public SurveyQuestion(Integer id, String name, String description, SurveyQuestionType type, List<SurveySelectInput> selectInputs, boolean required) {
        if (type.isSelectable()) {
            if (CollectionUtils.isEmpty(selectInputs)) {
                throw new IllegalArgumentException("Select inputs cannot be empty");
            }
            this.selectInputs = selectInputs;
        } else {
            this.selectInputs = null;
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.required = required;
    }

    public static SurveyQuestion create(String name, String description, SurveyQuestionType type, List<String> selectInputs, boolean required) {
        return new SurveyQuestion(null, name, description, type, selectInputWithValue(selectInputs), required);
    }

    private static List<SurveySelectInput> selectInputWithValue(List<String> inputs) {
        if (inputs == null) {
            return null;
        }
        List<SurveySelectInput> result = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            result.add(new SurveySelectInput(i, inputs.get(i)));
        }
        return result;
    }

    public SurveyQuestion withId(Integer id) {
        return new SurveyQuestion(id, name, description, type, selectInputs, required);
    }
}
