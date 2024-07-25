package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FormTest {
    @Test
    fun `Form을 생성하고 생성자의 필드 값을 리턴한다`() {
        val form =
            Form(
                name = "Test Form",
                description = "This is a test form",
                questions = listOf(),
                responses = listOf(),
            )

        assertThat(form.formId).isZero
        assertThat(form.name).isEqualTo("Test Form")
        assertThat(form.description).isEqualTo("This is a test form")
    }
}
