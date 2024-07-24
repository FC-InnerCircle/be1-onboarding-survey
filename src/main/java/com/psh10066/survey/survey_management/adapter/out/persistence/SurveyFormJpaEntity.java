package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyForm;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(
    name = "survey_form",
    indexes = {
        @Index(columnList = "surveyId, version", unique = true)
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyFormJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID surveyId;

    @Column(nullable = false)
    private Long version;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 65535)
    @Convert(converter = SurveyQuestionsConverter.class)
    private List<SurveyQuestion> questions;

    private SurveyFormJpaEntity(UUID surveyId, Long version, String name, String description, List<SurveyQuestion> questions) {
        this.surveyId = surveyId;
        this.version = version;
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    public static SurveyFormJpaEntity from(Survey.SurveyId surveyId, SurveyForm surveyForm) {
        return new SurveyFormJpaEntity(
            surveyId.value(),
            surveyForm.getVersion(),
            surveyForm.getName(),
            surveyForm.getDescription(),
            surveyForm.getQuestions()
        );
    }
}
