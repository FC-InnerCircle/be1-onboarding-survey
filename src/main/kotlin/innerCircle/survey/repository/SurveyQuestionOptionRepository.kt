package innerCircle.survey.repository

import innerCircle.survey.entity.SurveyQuestionOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyQuestionOptionRepository : JpaRepository<SurveyQuestionOption, Long>
