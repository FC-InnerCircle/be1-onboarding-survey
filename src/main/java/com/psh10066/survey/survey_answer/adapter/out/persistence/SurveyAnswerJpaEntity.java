package com.psh10066.survey.survey_answer.adapter.out.persistence;

import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import com.psh10066.survey.survey_answer.domain.SurveyQuestionAnswer;
import com.psh10066.survey.survey_management.domain.Survey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "survey_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyAnswerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID surveyId;

    @Column(nullable = false)
    private Long surveyVersion;

    @Column(nullable = false, length = 65535)
    @Convert(converter = SurveyQuestionAnswersConverter.class)
    private List<SurveyQuestionAnswer> answers;

    public static SurveyAnswerJpaEntity from(SurveyAnswer surveyAnswer) {
        return new SurveyAnswerJpaEntity(
            surveyAnswer.getId(),
            surveyAnswer.getSurveyId().value(),
            surveyAnswer.getSurveyVersion(),
            surveyAnswer.getAnswers()
        );
    }

    public SurveyAnswer toModel() {
        return new SurveyAnswer(id, new Survey.SurveyId(surveyId), surveyVersion, answers);
    }
}
