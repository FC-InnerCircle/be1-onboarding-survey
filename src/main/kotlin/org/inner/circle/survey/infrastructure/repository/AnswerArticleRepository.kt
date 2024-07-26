package org.inner.circle.survey.infrastructure.repository

import org.inner.circle.survey.domain.entity.Answer
import org.inner.circle.survey.domain.entity.AnswerArticle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerArticleRepository : JpaRepository<AnswerArticle, Long> {
    fun findAllByAnswer(answer: Answer): List<AnswerArticle>
}
