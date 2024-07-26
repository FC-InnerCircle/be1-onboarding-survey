package org.example.survey.application

import jakarta.persistence.EntityNotFoundException
import org.example.survey.domain.Form
import org.example.survey.repository.FormRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FormRegisterService(
    private val formRepository: FormRepository,
) {
    @Transactional
    fun register(form: Form): Long = formRepository.save(form).formId

    @Transactional
    fun updateForm(
        formId: Long,
        updatedForm: Form,
    ): Long {
        val form =
            formRepository
                .findById(formId)
                .orElseThrow { EntityNotFoundException("$formId not found") }
        form.updateForm(updatedForm)
        return formId
    }
}
