package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveyQuestionRequestDto;
import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ModifySurveyRequestTest {

    @DisplayName("커맨드로 변환할 수 있다.")
    @Test
    void toCommand() {
        // given
        UUID uuid = UUID.randomUUID();
        ModifySurveyRequest request = new ModifySurveyRequest(
            uuid,
            "name",
            "description",
            List.of(new SurveyQuestionRequestDto(
                "questionName",
                "questionDescription",
                SurveyQuestionType.RADIO,
                List.of("그렇다", "아니다"),
                true
            ))
        );

        // when
        ModifySurveyCommand command = request.toCommand();

        // then
        assertThat(command.getId()).isEqualTo(uuid);
        assertThat(command.getName()).isEqualTo("name");
        assertThat(command.getDescription()).isEqualTo("description");
        assertThat(command.getQuestions()).hasSize(1);

        SurveyQuestionDto question = command.getQuestions().getFirst();
        assertThat(question.name()).isEqualTo("questionName");
        assertThat(question.description()).isEqualTo("questionDescription");
        assertThat(question.type()).isEqualTo(SurveyQuestionType.RADIO);
        assertThat(question.selectInputs()).hasSize(2);
        assertThat(question.required()).isTrue();

        List<String> surveySelectInputs = question.selectInputs();
        assertThat(surveySelectInputs.get(0)).isEqualTo("그렇다");
        assertThat(surveySelectInputs.get(1)).isEqualTo("아니다");
    }
}