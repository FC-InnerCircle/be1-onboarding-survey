package innerCircle.survey.repository

import innerCircle.survey.entity.Survey
import innerCircle.survey.entity.SurveyVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SurveyVersionRepository : JpaRepository<SurveyVersion, Long>{
    fun findBySurvey(survey: Survey): List<SurveyVersion>

    @Query("SELECT sv FROM SurveyVersion sv WHERE sv.survey = :survey ORDER BY sv.createdAt DESC")
    fun findLatestBySurvey(@Param("survey") survey: Survey): SurveyVersion?

}
