package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyForm;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import({SurveyPersistenceAdapter.class, ObjectMapper.class})
@DataJpaTest
class SurveyPersistenceAdapterTest {

    @Autowired
    private SurveyPersistenceAdapter surveyPersistenceAdapter;

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired
    private SurveyFormJpaRepository surveyFormJpaRepository;

    @DisplayName("설문조사를 등록할 수 있다.")
    @Test
    void register() {
        // given
        List<SurveyQuestion> questions = List.of(SurveyQuestion.create(
            "questionName",
            "questionDescription",
            SurveyQuestionType.RADIO,
            List.of("그렇다", "아니다"),
            true
        ).withId(0));
        SurveyForm surveyForm = SurveyForm.create(
            "name",
            "description",
            questions
        );
        Survey survey = Survey.create(surveyForm);

        // when
        surveyPersistenceAdapter.register(survey);

        // then
        Optional<SurveyJpaEntity> surveyJpaEntityOpt = surveyJpaRepository.findById(survey.getId().value());
        assertThat(surveyJpaEntityOpt.isPresent()).isTrue();
        SurveyJpaEntity surveyJpaEntity = surveyJpaEntityOpt.get();

        Optional<SurveyFormJpaEntity> surveyFormJpaEntityOpt = surveyFormJpaRepository.findBySurveyIdAndVersion(survey.getId().value(), surveyForm.getVersion());
        assertThat(surveyFormJpaEntityOpt.isPresent()).isTrue();
        SurveyFormJpaEntity surveyFormJpaEntity = surveyFormJpaEntityOpt.get();

        assertThat(surveyJpaEntity.getLatestVersion()).isEqualTo(surveyFormJpaEntity.getVersion());

        assertThat(surveyFormJpaEntity.getName()).isEqualTo("name");
        assertThat(surveyFormJpaEntity.getDescription()).isEqualTo("description");
        assertThat(surveyFormJpaEntity.getQuestions()).isEqualTo(questions);
        assertThat(surveyFormJpaEntity.getCreatedAt()).isNotNull();
        assertThat(surveyFormJpaEntity.getUpdatedAt()).isNotNull();
    }
}