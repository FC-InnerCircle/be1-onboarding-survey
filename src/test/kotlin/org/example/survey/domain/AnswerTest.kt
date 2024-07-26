package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.enums.InputType
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AnswerTest {
    @Test
    fun `Answer is created with given values and returns them`() {
        val form =
            Form(
                name = "Test Form",
                description = "This is a test form",
            )

        val question =
            Question(
                name = "Test Question",
                description = "This is a test question",
                inputType = InputType.MULTI_CHOICE,
                required = true,
            )

        val response =
            Response(
                respondedAt = LocalDateTime.now(),
                form = form,
            )

        val answer =
            Answer(
                response = response,
                question = question,
                content = "This is a test answer",
            )

        // Validating the default values and associations
        assertThat(answer.answerId).isZero
        assertThat(answer.content).isEqualTo("This is a test answer")
        assertThat(answer.response).isEqualTo(response)
        assertThat(answer.question).isEqualTo(question)
    }
}
