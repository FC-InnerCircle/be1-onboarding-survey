package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.enums.InputType
import org.junit.jupiter.api.Test

class QuestionTest {
    @Test
    fun `Question을 생성하고 생성된 값을 리턴한다`() {
        val question =
            Question(
                name = "Test Question",
                description = "This is a test question",
                inputType = InputType.SINGLE_CHOICE,
                required = true,
            )

        assertThat(question.questionId).isZero
        assertThat(question.name).isEqualTo("Test Question")
        assertThat(question.description).isEqualTo("This is a test question")
        assertThat(question.inputType).isEqualTo(InputType.SINGLE_CHOICE)
        assertThat(question.required).isTrue
    }
}
