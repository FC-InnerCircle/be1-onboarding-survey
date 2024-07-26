package org.example.survey.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
}
