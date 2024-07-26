package innerCircle.survey.repository

import innerCircle.survey.entity.SurveyResponseAnswer
import innerCircle.survey.entity.SurveyResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyResponseAnswerRepository : JpaRepository<SurveyResponseAnswer, Long> {
    fun findByResponse(response: SurveyResponse): List<SurveyResponseAnswer>
}
