package org.example.survey.ui

import org.example.survey.application.FormRegisterService
import org.example.survey.dto.SurveyRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/surveys")
class SurveyController(
    private val formRegisterService: FormRegisterService,
) {
    @PostMapping
    fun registerSurvey(
        @RequestBody survey: SurveyRequest,
    ): ResponseEntity<Long> {
        val formId = formRegisterService.register(survey.toFormEntity())
        return ResponseEntity.ok(formId)
    }

    @PutMapping("/{formId}")
    fun updateSurvey(
        @PathVariable formId: Long,
        @RequestBody survey: SurveyRequest,
    ): ResponseEntity<Long> = ResponseEntity.ok(formRegisterService.updateForm(formId, survey.toFormEntity()))
}
