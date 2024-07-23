package org.inner.circle.survey.adapter.repository

import org.inner.circle.survey.domain.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long>
