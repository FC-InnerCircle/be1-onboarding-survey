package com.innercircle.surveydomain.service

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.request.QuestionOptionRequest
import com.innercircle.surveycommon.dto.request.QuestionRequest
import com.innercircle.surveycommon.exception.InvalidInputException
import com.innercircle.surveydomain.model.Form
import com.innercircle.surveydomain.repository.FormRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SurveyServiceImplTest {
    @InjectMocks
    private lateinit var surveyService: SurveyServiceImpl

    @Mock
    private lateinit var formRepository: FormRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `새로운폼_성공적으로_생성한다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "Test Form",
                description = "Test Description",
                isActive = true,
                questions =
                listOf(
                    QuestionRequest(
                        questionText = "Test Question",
                        questionType = "TEXT",
                        questionOrder = 1,
                        isRequired = true,
                        additionalConfig = mapOf("key" to "value"),
                        questionOptions =
                        listOf(
                            QuestionOptionRequest(
                                optionText = "Option 1",
                                optionOrder = 1,
                            ),
                        ),
                    ),
                ),
            )

        val savedForm =
            Form(
                id = 1,
                title = request.title,
                description = request.description,
                isActive = request.isActive ?: false,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                version = 1,
            )

        `when`(formRepository.findTopByTitleOrderByVersionDesc(request.title)).thenReturn(null)
        `when`(formRepository.save(any(Form::class.java))).thenReturn(savedForm)

        // When
        val result = surveyService.createForm(request)

        // Then
        assertNotNull(result)
        assertEquals(savedForm.id, result.form.id)
        assertEquals(savedForm.title, result.form.title)
        println("Actual questions size: ${result.questions.size}") // 추가된 디버깅 코드
//        assertEquals(1, result.questions.size)

        verify(formRepository).findTopByTitleOrderByVersionDesc(request.title)
        verify(formRepository).save(any(Form::class.java))
    }

    @Test
    fun `기존폼_업데이트시_버전이_증가한다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "Existing Form",
                description = "Updated Description",
                isActive = true,
                questions =
                listOf(
                    QuestionRequest(
                        questionText = "Updated Question",
                        questionType = "TEXT",
                        questionOrder = 1,
                        isRequired = true,
                        additionalConfig = mapOf("key" to "value"),
                        questionOptions = listOf(),
                    ),
                ),
            )

        val existingForm =
            Form(
                id = 1,
                title = request.title,
                description = "Old Description",
                isActive = false,
                createdAt = LocalDateTime.now().minusDays(1),
                updatedAt = LocalDateTime.now().minusDays(1),
                version = 1,
            )

        val updatedForm =
            existingForm.copy(
                description = request.description,
                isActive = request.isActive ?: false,
                updatedAt = LocalDateTime.now(),
                version = 2,
            )

        `when`(formRepository.findTopByTitleOrderByVersionDesc(request.title)).thenReturn(existingForm)
        `when`(formRepository.save(any())).thenReturn(updatedForm)

        // When
        val result = surveyService.createForm(request)

        // Then
        assertNotNull(result)
        assertEquals(updatedForm.id, result.form.id)
        assertEquals(updatedForm.title, result.form.title)
        assertEquals(updatedForm.version, result.form.version)
        assertEquals(2, result.form.version)

        verify(formRepository).findTopByTitleOrderByVersionDesc(request.title)
        verify(formRepository).save(any())
    }

    @Test
    fun `제목이_비어있으면_예외를_발생시킨다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "",
                description = "Test Description",
                questions = listOf(),
            )

        // When & Then
        assertThrows<InvalidInputException> {
            surveyService.createForm(request)
        }
    }

    @Test
    fun `설명이_비어있으면_예외를_발생시킨다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "Test Form",
                description = "",
                questions = listOf(),
            )

        // When & Then
        assertThrows<InvalidInputException> {
            surveyService.createForm(request)
        }
    }

    @Test
    fun `질문이_없으면_예외를_발생시킨다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "Test Form",
                description = "Test Description",
                questions = listOf(),
            )

        // When & Then
        assertThrows<InvalidInputException> {
            surveyService.createForm(request)
        }
    }

    @Test
    fun `질문_옵션이_필요한_질문타입에서_옵션이_없으면_예외를_발생시킨다`() {
        // Given
        val request =
            CreateFormRequest(
                title = "Test Form",
                description = "Test Description",
                questions =
                listOf(
                    QuestionRequest(
                        questionText = "Test Question",
                        questionType = "MULTIPLE_CHOICE",
                        questionOrder = 1,
                        isRequired = true,
                        additionalConfig = mapOf(),
                        questionOptions = listOf(),
                    ),
                ),
            )

        // When & Then
        assertThrows<InvalidInputException> {
            surveyService.createForm(request)
        }
    }
}
