package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class QuestionTest {
    @Test
    fun `Question을 생성하고 생성된 값을 리턴한다`() {
        val question =
            Question(
                form =
                    Form(
                        name = "Test Form",
                        description = "This is a test form",
                    ),
                name = "Test Question",
                description = "This is a test question",
                inputTypes = "단일 선택",
                required = true,
            )

        assertThat(question.questionId).isZero
        assertThat(question.form.name).isEqualTo("Test Form")
        assertThat(question.name).isEqualTo("Test Question")
        assertThat(question.description).isEqualTo("This is a test question")
        assertThat(question.inputTypes).isEqualTo("단일 선택")
        assertThat(question.required).isTrue
    }
}
