package com.psh10066.survey.survey_management.application.service;

import com.psh10066.mock.survey_management.adapter.out.persistence.FakeSurveyRepository;
import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import com.psh10066.survey.survey_management.domain.SurveySelectInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RegisterSurveyServiceTest {

    private RegisterSurveyService registerSurveyService;
    private FakeSurveyRepository fakeSurveyRepository;

    @BeforeEach
    void setUp() {
        fakeSurveyRepository = new FakeSurveyRepository();
        registerSurveyService = new RegisterSurveyService(fakeSurveyRepository);
    }

    @DisplayName("설문조사를 등록할 수 있다.")
    @Test
    void registerSurvey() {
        // given
        RegisterSurveyCommand command = new RegisterSurveyCommand(
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
        Survey.SurveyId surveyId = registerSurveyService.registerSurvey(command);

        // then
        assertThat(surveyId).isNotNull();
        assertThat(fakeSurveyRepository.data).hasSize(1);

        Survey survey = fakeSurveyRepository.data.getFirst();
        assertThat(surveyId.getValue()).isEqualTo(survey.getId().getValue());
        assertThat(survey.getForm().getVersion()).isEqualTo(1);
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