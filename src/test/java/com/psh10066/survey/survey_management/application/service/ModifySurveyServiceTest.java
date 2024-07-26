package com.psh10066.survey.survey_management.application.service;

import com.psh10066.mock.survey_management.adapter.out.persistence.FakeSurveyPersistenceAdapter;
import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ModifySurveyServiceTest {

    private ModifySurveyService modifySurveyService;
    private FakeSurveyPersistenceAdapter fakeSurveyPersistenceAdapter;

    private UUID testSurveyId;

    @BeforeEach
    void setUp() {
        fakeSurveyPersistenceAdapter = new FakeSurveyPersistenceAdapter();
        fakeSurveyPersistenceAdapter.data.add(testSurvey());

        modifySurveyService = new ModifySurveyService(fakeSurveyPersistenceAdapter, fakeSurveyPersistenceAdapter);
    }

    private Survey testSurvey() {
        List<SurveyQuestion> questions = List.of(SurveyQuestion.create(
            "beforeQuestionName",
            "beforeQuestionDescription",
            SurveyQuestionType.CHECKBOX,
            List.of("맞다", "틀리다"),
            false
        ));
        SurveyForm surveyForm = SurveyForm.create(
            "beforeName",
            "beforeDescription",
            questions
        );
        Survey survey = Survey.create(surveyForm);
        this.testSurveyId = survey.getId().value();
        return survey;
    }

    @DisplayName("설문조사를 수정할 수 있다.")
    @Test
    void modifySurvey() {
        // given
        ModifySurveyCommand command = new ModifySurveyCommand(
            testSurveyId,
            "name",
            "description",
            List.of(new SurveyQuestionDto(
                "questionName",
                "questionDescription",
                SurveyQuestionType.RADIO,
                List.of("그렇다", "아니다"),
                true
            ))
        );

        // when
        Survey.SurveyId surveyId = modifySurveyService.modifySurvey(command);

        // then
        assertThat(surveyId).isNotNull();
        assertThat(fakeSurveyPersistenceAdapter.data).hasSize(1);

        Survey survey = fakeSurveyPersistenceAdapter.data.getFirst();
        assertThat(surveyId.value()).isEqualTo(survey.getId().value());
        assertThat(survey.getForm().getVersion()).isEqualTo(2);
        assertThat(survey.getForm().getName()).isEqualTo("name");
        assertThat(survey.getForm().getDescription()).isEqualTo("description");
        assertThat(survey.getForm().getQuestions()).hasSize(1);

        SurveyQuestion question = survey.getForm().getQuestions().getFirst();
        assertThat(question.name()).isEqualTo("questionName");
        assertThat(question.description()).isEqualTo("questionDescription");
        assertThat(question.type()).isEqualTo(SurveyQuestionType.RADIO);
        assertThat(question.selectInputs()).hasSize(2);
        assertThat(question.required()).isTrue();

        List<SurveySelectInput> surveySelectInputs = question.selectInputs();
        assertThat(surveySelectInputs.get(0).text()).isEqualTo("그렇다");
        assertThat(surveySelectInputs.get(1).text()).isEqualTo("아니다");
    }
}