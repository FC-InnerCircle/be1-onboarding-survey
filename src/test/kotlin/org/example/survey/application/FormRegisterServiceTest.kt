package org.example.survey.application

import org.assertj.core.api.Assertions.assertThat
import org.example.survey.domain.Form
import org.example.survey.repository.FormRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.Optional

class FormRegisterServiceTest {
    private lateinit var formRepository: FormRepository
    private lateinit var formRegisterService: FormRegisterService

    @BeforeEach
    fun setup() {
        formRepository = mock(FormRepository::class.java)
        formRegisterService = FormRegisterService(formRepository)
    }

    @Test
    fun `Form을 저장하고 formId를 리턴한다`() {
        val form = Form(name = "Test Form", description = "This is a test form")
        `given`(formRepository.save(form)).willReturn(form)

        val formId = formRegisterService.register(form)

        assertThat(formId).isEqualTo(form.formId)
        verify(formRepository).save(form)
    }

    @Test
    fun `Form을 수정하고 formId를 리턴한다`() {
        val formId = 1L
        val form = Form(name = "Form", description = "form description")
        `given`(formRepository.findById(formId)).willReturn(Optional.of(form))

        val updatedForm = Form(name = "Updated Form", description = "updated form")
        val updatedFormId = formRegisterService.updateForm(formId, updatedForm)

        assertThat(updatedFormId).isEqualTo(formId)
        verify(formRepository).findById(formId)
    }
}
