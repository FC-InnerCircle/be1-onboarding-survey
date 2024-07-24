package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveyQuestionRequestDto;
import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveySelectInputRequestDto;
import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveySelectInputDto;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RegisterSurveyRequestTest {

    @DisplayName("커맨드로 변환할 수 있다.")
    @Test
    void toCommand() {
        // given
        RegisterSurveyRequest request = new RegisterSurveyRequest(
            "name",
            "description",
            List.of(new SurveyQuestionRequestDto(
                "questionName",
                "questionDescription",
                SurveyQuestionType.RADIO,
                List.of(new SurveySelectInputRequestDto(1, "1번")),
                true
            ))
        );

        // when
        RegisterSurveyCommand command = request.toCommand();

        // then
        assertThat(command.getName()).isEqualTo("name");
        assertThat(command.getDescription()).isEqualTo("description");
        assertThat(command.getQuestions()).hasSize(1);

        SurveyQuestionDto question = command.getQuestions().getFirst();
        assertThat(question.name()).isEqualTo("questionName");
        assertThat(question.description()).isEqualTo("questionDescription");
        assertThat(question.type()).isEqualTo(SurveyQuestionType.RADIO);
        assertThat(question.selectInputs()).hasSize(1);
        assertThat(question.required()).isTrue();

        SurveySelectInputDto surveySelectInput = question.selectInputs().getFirst();
        assertThat(surveySelectInput.inputValue()).isEqualTo(1);
        assertThat(surveySelectInput.text()).isEqualTo("1번");
    }
}