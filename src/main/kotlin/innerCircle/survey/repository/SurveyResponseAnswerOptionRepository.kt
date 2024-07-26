package innerCircle.survey.repository

import innerCircle.survey.entity.SurveyQuestion
import innerCircle.survey.entity.SurveyResponse
import innerCircle.survey.entity.SurveyResponseAnswerOption
import org.springframework.data.jpa.repository.JpaRepository

interface SurveyResponseAnswerOptionRepository : JpaRepository<SurveyResponseAnswerOption, Long> {
    fun findByQuestionAndResponse(question: SurveyQuestion, response: SurveyResponse): List<SurveyResponseAnswerOption>
}
