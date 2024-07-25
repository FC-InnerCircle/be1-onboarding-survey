package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.enums.InputType
import org.junit.jupiter.api.Test

class OptionTest {
    @Test
    fun `Option을 생성하고 생성자에 인자를 리턴한다`() {
        val question =
            Question(
                form =
                    Form(
                        name = "Test Form",
                        description = "This is a test form",
                    ),
                name = "Test Question",
                description = "This is a test question",
                inputType = InputType.SINGLE_CHOICE,
                required = true,
            )

        val option =
            Option(
                question = question,
                content = "Option 1",
                seq = 1,
            )

        assertThat(option.optionId).isZero
        assertThat(option.content).isEqualTo("Option 1")
        assertThat(option.seq).isEqualTo(1)
        assertThat(option.question).isEqualTo(question)
    }
}
