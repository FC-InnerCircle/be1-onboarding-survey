package com.psh10066.survey.survey_management.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SurveyForm {
    private final Long version;
    private final String name;
    private final String description;
    private final List<SurveyQuestion> questions;

    private SurveyForm(Long version, String name, String description, List<SurveyQuestion> questions) {
        this.version = version;
        this.name = name;
        this.description = description;
        this.questions = this.questionsWithId(questions);
    }

    public static SurveyForm create(String name, String description, List<SurveyQuestion> questions) {
        return new SurveyForm(1L, name, description, questions);
    }

    public static SurveyForm update(Long version, String name, String description, List<SurveyQuestion> questions) {
        return new SurveyForm(version, name, description, questions);
    }

    private List<SurveyQuestion> questionsWithId(List<SurveyQuestion> questions) {
        if (questions == null) {
            return null;
        }
        List<SurveyQuestion> result = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            SurveyQuestion question = questions.get(i);
            result.add(question.withId(i));
        }
        return result;
    }
}
