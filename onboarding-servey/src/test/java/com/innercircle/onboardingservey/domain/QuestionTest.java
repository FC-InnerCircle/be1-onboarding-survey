package com.innercircle.onboardingservey.domain;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;


public class QuestionTest {

    @Test
    @DisplayName("텍스트 기반의 질문을 생성할 수 있다.")
    void create_question_text_test_success() {
        final Survey survey = new Survey("텍스트 기반 질문", "텍스트 기반의 질문을 생성한다.");
        final String title = "이름을 입력해주세요";
        final String description = "본인의 이름을 성과 함께 입력해주세요";
        final Boolean required = Boolean.TRUE;
        final Question question = Question.shortAnswer(
            title, description, required, survey);

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getDescription()).isEqualTo(description);
        assertThat(question.getRequired()).isEqualTo(required);
    }
    @Test
    @DisplayName("장문형 질문을 생성할 수 있다.")
    void create_question_long_test_success() {
        final Survey survey = new Survey("장문형 질문", "장문형 질문을 생성한다.");
        final String title = "이름을 입력해주세요";
        final String description = "본인의 이름을 성과 함께 입력해주세요";
        final Boolean required = Boolean.TRUE;
        final Question question = Question.longAnswer(
            title, description, required, survey);

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getDescription()).isEqualTo(description);
        assertThat(question.getRequired()).isEqualTo(required);
    }

    @Test
    @DisplayName("단일 선택 리스트를 생성할 수 있다.")
    void create_single_choice_test_success() {
        final Survey survey = new Survey("단일 선택 리스트 질문", "단문 선택 리스트 질문을 생성한다.");
        final String title = "이름을 입력해주세요";
        final Boolean required = Boolean.TRUE;
        final List<QuestionOption> questionOptions = IntStream.of(1,2,3,4,5)
            .mapToObj(i -> new QuestionOption("선택 " + i, i))
            .toList();

        final Question question = Question.singleChoice(
            title, required, survey, questionOptions);

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getRequired()).isEqualTo(required);
        assertThat(question.getQuestionOptions()).isNotEmpty();
        assertThat(question.getQuestionOptions().size()).isEqualTo(5);
    }
    @Test
    @DisplayName("멀티 선택 리스트를 생성할 수 있다.")
    void create_multi_choice_test_success() {
        final Survey survey = new Survey("단일 선택 리스트 질문", "단문 선택 리스트 질문을 생성한다.");
        final String title = "이름을 입력해주세요";
        final Boolean required = Boolean.TRUE;
        final List<QuestionOption> questionOptions = IntStream.of(1,2,3,4,5)
            .mapToObj(i -> new QuestionOption("선택 " + i, i))
            .toList();

        final Question question = Question.multiChoice(
            title, required, survey, questionOptions);

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getRequired()).isEqualTo(required);
        assertThat(question.getQuestionOptions()).isNotEmpty();
        assertThat(question.getQuestionOptions().size()).isEqualTo(5);
    }
}
