package org.example.survey.application

import org.example.survey.domain.Form
import org.example.survey.repository.FormRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FormRegisterService(
    val formRepository: FormRepository,
) {
    @Transactional
    fun register(form: Form): Long = formRepository.save(form).formId
}
