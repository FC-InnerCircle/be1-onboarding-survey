package com.psh10066.survey.survey_answer.domain;

import java.util.List;

public record SurveyQuestionAnswer(
    Integer surveyQuestionId,
    String textAnswer,
    List<Integer> selectInputValue
) {
}
