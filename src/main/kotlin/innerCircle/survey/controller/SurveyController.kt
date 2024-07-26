package innerCircle.survey.controller

import innerCircle.survey.dto.CreateSurveyRequest
import innerCircle.survey.dto.DeleteQuestionRequest
import innerCircle.survey.dto.UpdateSurveyRequest
import innerCircle.survey.service.SurveyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/surveys")
class SurveyController(
    private val surveyService: SurveyService
) {

    @PostMapping
    fun createSurvey(@RequestBody request: CreateSurveyRequest): ResponseEntity<Void> {
        surveyService.createSurvey(request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{surveyId}")
    fun updateSurvey(
        @PathVariable surveyId: Long,
        @RequestBody request: UpdateSurveyRequest
    ): ResponseEntity<Void> {
        surveyService.updateQuestions(surveyId, request)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{surveyId}/questions")
    fun addQuestions(
        @PathVariable surveyId: Long,
        @RequestBody request: UpdateSurveyRequest
    ): ResponseEntity<Void> {
        surveyService.addQuestions(surveyId, request)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{surveyId}/questions/{questionId}")
    fun deleteSingleQuestion(
        @PathVariable surveyId: Long,
        @PathVariable questionId: Long
    ): ResponseEntity<Void> {
        try {
            surveyService.deleteSingleQuestion(surveyId, questionId)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{surveyId}/questions")
    fun deleteMultipleQuestions(
        @PathVariable surveyId: Long,
        @RequestBody request: DeleteQuestionRequest
    ): ResponseEntity<Void> {
        try {
            surveyService.deleteMultipleQuestions(surveyId, request.questionIds)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}
