package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.survey.survey_management.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import({SurveyRepository.class, ObjectMapper.class})
@DataJpaTest
class SurveyRepositoryTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyJpaRepository surveyJpaRepository;

    @Autowired
    private SurveyFormJpaRepository surveyFormJpaRepository;

    @DisplayName("설문조사를 등록할 수 있다.")
    @Test
    void register() {
        // given
        List<SurveyQuestion> questions = List.of(new SurveyQuestion(
            "questionName",
            "questionDescription",
            SurveyQuestionType.RADIO,
            List.of(new SurveySelectInput(1, "1번")),
            true
        ));
        SurveyForm surveyForm = SurveyForm.create(
            "name",
            "description",
            questions
        );
        Survey survey = Survey.create(surveyForm);

        // when
        surveyRepository.register(survey);

        // then
        Optional<SurveyJpaEntity> surveyJpaEntityOpt = surveyJpaRepository.findById(survey.getId().getValue());
        assertThat(surveyJpaEntityOpt.isPresent()).isTrue();
        SurveyJpaEntity surveyJpaEntity = surveyJpaEntityOpt.get();

        Optional<SurveyFormJpaEntity> surveyFormJpaEntityOpt = surveyFormJpaRepository.findBySurveyIdAndVersion(survey.getId().getValue(), surveyForm.getVersion());
        assertThat(surveyFormJpaEntityOpt.isPresent()).isTrue();
        SurveyFormJpaEntity surveyFormJpaEntity = surveyFormJpaEntityOpt.get();

        assertThat(surveyJpaEntity.getLatestVersion()).isEqualTo(surveyFormJpaEntity.getVersion());

        assertThat(surveyFormJpaEntity.getName()).isEqualTo("name");
        assertThat(surveyFormJpaEntity.getDescription()).isEqualTo("description");
        assertThat(surveyFormJpaEntity.getQuestions()).isEqualTo(questions);
    }
}