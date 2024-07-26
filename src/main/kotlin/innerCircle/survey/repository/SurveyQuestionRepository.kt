package innerCircle.survey.repository

import innerCircle.survey.entity.SurveyQuestion
import innerCircle.survey.entity.SurveyVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface SurveyQuestionRepository : JpaRepository<SurveyQuestion, Long>{

    fun findBySurveyVersion(surveyVersion: SurveyVersion): List<SurveyQuestion>

    @Modifying
    @Query("UPDATE SurveyQuestion q SET q.deletedAt = :deletedAt WHERE q.id IN :ids")
    fun markAsDeleted(@Param("ids") ids: List<Long>, @Param("deletedAt") deletedAt: LocalDateTime)
}

