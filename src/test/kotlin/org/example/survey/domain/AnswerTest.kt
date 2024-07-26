package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AnswerTest {
    @Test
    fun `Answer 를 생성하고 생성정보를 리턴`() {
        val answer =
            Answer(
                content = "This is a test answer",
                questionId = 1,
            )

        assertThat(answer.answerId).isEqualTo(0)
        assertThat(answer.content).isEqualTo("This is a test answer")
    }
}
