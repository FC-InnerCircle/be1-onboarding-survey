package com.psh10066.survey.survey_answer.domain;

import com.psh10066.survey.survey_management.domain.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class SurveyAnswer {

    private final Long id;
    private final Survey.SurveyId surveyId;
    private final Long surveyVersion;
    private final List<SurveyQuestionAnswer> answers;
    private final LocalDateTime createdAt;

    public static SurveyAnswer create(Survey.SurveyId surveyId, Long surveyVersion, List<SurveyQuestionAnswer> answers) {
        return new SurveyAnswer(null, surveyId, surveyVersion, answers, null);
    }
}
