package innerCircle.survey.controller

import innerCircle.survey.dto.SurveyResponseRequest
import innerCircle.survey.dto.SurveyResponseQuery
import innerCircle.survey.dto.SurveyResponseDto
import innerCircle.survey.service.SurveyResponseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/surveys")
class SurveyResponseController(
    private val surveyResponseService: SurveyResponseService
) {

    @PostMapping("/responses")
    fun submitSurveyResponse(@RequestBody request: SurveyResponseRequest): ResponseEntity<String> {
        surveyResponseService.submitSurveyResponse(request)
        return ResponseEntity.ok("Survey response submitted successfully.")
    }

    @GetMapping("/responses")
    fun getSurveyResponses(
        @RequestParam surveyId: Long,
        @RequestParam(required = false) questionName: String?,
        @RequestParam(required = false) responseValue: String?
    ): ResponseEntity<List<SurveyResponseDto>> {
        val responses = surveyResponseService.getSurveyResponses(surveyId, questionName, responseValue)
        return ResponseEntity.ok(responses)
    }
}
