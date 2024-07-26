package org.inner.circle.survey.infrastructure.repository

import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.Survey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findBySurvey(survey: Survey): List<Question>

    fun findByIdAndSurvey(
        questionId: Long,
        survey: Survey,
    ): Question?
}
