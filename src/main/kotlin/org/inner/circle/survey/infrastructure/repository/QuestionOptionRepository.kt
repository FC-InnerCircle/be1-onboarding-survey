package org.inner.circle.survey.infrastructure.repository

import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.QuestionOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionOptionRepository : JpaRepository<QuestionOption, Long> {
    fun findByQuestion(question: Question): List<QuestionOption>
}
