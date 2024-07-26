package org.example.survey.application

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.Answer
import org.example.survey.domain.Feedback
import org.example.survey.domain.Form
import org.example.survey.domain.Question
import org.example.survey.domain.enums.InputType
import org.example.survey.repository.FeedbackRepository
import org.example.survey.repository.FormRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.given
import java.util.Optional

class FeedbackRegisterServiceTest {
    private lateinit var feedbackRepository: FeedbackRepository
    private lateinit var formRepository: FormRepository
    private lateinit var feedbackRegisterService: FeedbackRegisterService

    @BeforeEach
    fun setUp() {
        feedbackRepository = mock(FeedbackRepository::class.java)
        formRepository = mock(FormRepository::class.java)
        feedbackRegisterService = FeedbackRegisterService(feedbackRepository, formRepository)
    }

    @Test
    fun `설문 피드백을 등록하고 id를 리턴한다`() {
        val feedbackId = 0L
        val formId = 0L
        val feedback = Feedback(formId, listOf(Answer("Answer", 1L)))
        given(formRepository.findById(formId)).willReturn(Optional.of(Form()))
        given(feedbackRepository.save(feedback)).willReturn(feedback)

        val result = feedbackRegisterService.register(feedback)

        verify(feedbackRepository).save(feedback)
        assertThat(result).isEqualTo(feedbackId)
    }

    @Test
    fun `단일 선택 질문시, 답변이 여러개일 경우 예외를 던진다`() {
        val formId = 0L
        val questions =
            mutableListOf(
                Question(name = "test1", inputType = InputType.SINGLE_CHOICE, required = true),
            )
        val givenForm = Form(name = "test", description = "test", questions = questions, formId = formId)
        val answers = listOf(Answer("Answer", 1L), Answer("Answer", 1L))
        val feedback = Feedback(formId, answers)
        given(formRepository.findById(formId)).willReturn(Optional.of(givenForm))

        assertThrows<IllegalArgumentException> { feedbackRegisterService.register(feedback) }
    }

    @Test
    fun `필수 질문시, 답변이 없을 경우 예외를 던진다`() {
        val formId = 0L
        val questions =
            mutableListOf(
                Question(name = "test1", inputType = InputType.SINGLE_CHOICE, required = true),
            )
        val givenForm = Form(name = "test", description = "test", questions = questions, formId = formId)
        val feedback = Feedback(formId, answers = emptyList())
        given(formRepository.findById(formId)).willReturn(Optional.of(givenForm))

        assertThrows<IllegalArgumentException> { feedbackRegisterService.register(feedback) }
    }
}
