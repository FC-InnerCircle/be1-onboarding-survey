package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.enums.InputType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FeedbackTest {
    @Test
    fun `Feedback 을 생성하고 생성한 인자 값을 리턴한다`() {
        val feedback =
            Feedback(
                formId = 1,
                answers = listOf(Answer(content = "This is a test answer", questionId = 1)),
            )

        assertThat(feedback.feedbackId).isEqualTo(0)
        assertThat(feedback.formId).isEqualTo(1)
        assertThat(feedback.answers[0].content).isEqualTo("This is a test answer")
        assertThat(feedback.answers[0].questionId).isEqualTo(1)
    }

    @Test
    fun `질문 유형 단일 답변일때 답변이 2개 이상이면 예외를 던진다`() {
        val answers =
            listOf(
                Answer(content = "This is a test answer", questionId = 1),
                Answer(content = "This is a test answer", questionId = 1),
            )
        val feedback =
            Feedback(
                formId = 1,
                answers =
                answers,
            )
        val questions =
            mutableListOf(
                Question(name = "test1", inputType = InputType.SINGLE_CHOICE, required = true),
            )

        assertThrows<IllegalArgumentException> { feedback.checkQuestions(Form(questions = questions)) }
    }

    @Test
    fun `질문 유형 단일 필수일때 답변이 없으면 예외를 던진다`() {
        val feedback =
            Feedback(
                formId = 1,
                answers = emptyList(),
            )
        val questions =
            mutableListOf(
                Question(name = "test1", inputType = InputType.SINGLE_CHOICE, required = true),
            )

        assertThrows<IllegalArgumentException> { feedback.checkQuestions(Form(questions = questions)) }
    }
}
