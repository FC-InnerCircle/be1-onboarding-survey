package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.enums.InputType
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ResponseTest {
    @Test
    fun `Response 을 생성하고 생성한 인자 값을 리턴한다`() {
        // Given
        val form =
            Form(
                name = "Test Form",
                description = "This is a test form",
            )
        val response =
            Response(
                respondedAt = LocalDateTime.now(),
                form = form,
            )
        val question =
            Question(
                name = "Test Question",
                description = "This is a test question",
                inputType = InputType.SINGLE_CHOICE,
                required = true,
            )

        // When
        val answer =
            Answer(
                response = response,
                question = question,
                content = "This is a test answer",
            )

        // Then
        assertThat(answer.answerId).isZero
        assertThat(answer.content).isEqualTo("This is a test answer")
        assertThat(answer.response).isEqualTo(response)
        assertThat(answer.question).isEqualTo(question)
    }
}
