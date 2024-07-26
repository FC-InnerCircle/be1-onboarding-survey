package com.psh10066.survey.survey_answer.domain;

import com.psh10066.survey.survey_answer.domain.exception.SurveyAnswerException;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyForm;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SurveyAnswerValidatorTest {

    private SurveyQuestion createSurveyQuestion(int i, SurveyQuestionType type, boolean required) {
        return SurveyQuestion.create(
            "questionName" + i,
            "questionDescription" + i,
            type,
            type.isSelectable() ? List.of("1번", "2번", "3번", "4번") : null,
            required
        );
    }

    private Survey createSurvey(List<SurveyQuestion> questions) {
        SurveyForm surveyForm = SurveyForm.create(
            "name",
            "description",
            questions
        );
        return Survey.create(surveyForm);
    }

    @DisplayName("설문조사 ID가 일치하지 않으면 실패한다.")
    @Test
    void verifyId() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            new Survey.SurveyId(UUID.randomUUID()),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, null, List.of(0)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("설문조사 버전이 일치하지 않으면 실패한다.")
    @Test
    void verifyVersion() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            2L,
            List.of(new SurveyQuestionAnswer(0, null, List.of(0)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("설문조사 응답 길이가 일치하지 않으면 실패한다.")
    @Test
    void verifySize() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, true),
            createSurveyQuestion(1, SurveyQuestionType.CHECKBOX, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, null, List.of(0)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("필수값이 아닌 경우 입력이 없어도 성공한다.")
    @Test
    void verifyRequired() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, false)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, null, null))
        );

        // when
        // then
        SurveyAnswerValidator.verify(survey, surveyAnswer);
    }

    @DisplayName("단답형의 경우 textAnswer가 없으면 실패한다.")
    @Test
    void verifyShort() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.SHORT, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, null, List.of(0)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("장문형의 경우 textAnswer가 없으면 실패한다.")
    @Test
    void verifyLong() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.LONG, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, " ", List.of(0)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("단일 선택 리스트의 경우 selectInputValue가 없으면 실패한다.")
    @Test
    void verifyRadio() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, "asd", null))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("단일 선택 리스트의 경우 selectInputValue가 2개이면 실패한다.")
    @Test
    void verifyRadio2() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.RADIO, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, "asd", List.of(0, 1)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("다중 선택 리스트의 경우 selectInputValue가 없으면 실패한다.")
    @Test
    void verifyCheckbox() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.CHECKBOX, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, "asd", List.of()))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }

    @DisplayName("선택 리스트의 경우 selectInputValue가 질문에 벗어난 값이 있으면 실패한다.")
    @Test
    void verifyQuestionInput() {
        // given
        List<SurveyQuestion> questions = List.of(
            createSurveyQuestion(0, SurveyQuestionType.CHECKBOX, true)
        );
        Survey survey = createSurvey(questions);

        SurveyAnswer surveyAnswer = SurveyAnswer.create(
            survey.getId(),
            survey.getForm().getVersion(),
            List.of(new SurveyQuestionAnswer(0, "asd", List.of(5)))
        );

        // when
        // then
        assertThatThrownBy(() ->
            SurveyAnswerValidator.verify(survey, surveyAnswer)
        ).isInstanceOf(SurveyAnswerException.class);
    }
}