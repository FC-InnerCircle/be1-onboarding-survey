package innerCircle.survey.repository

import innerCircle.survey.entity.Survey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyRepository : JpaRepository<Survey, Long>
