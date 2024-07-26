package innerCircle.survey.repository

import innerCircle.survey.entity.SurveyQuestion
import innerCircle.survey.entity.SurveyResponse
import innerCircle.survey.entity.SurveyVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyResponseRepository : JpaRepository<SurveyResponse, Long> {
    fun findBySurveyVersion(surveyVersion: SurveyVersion): List<SurveyResponse>
}
