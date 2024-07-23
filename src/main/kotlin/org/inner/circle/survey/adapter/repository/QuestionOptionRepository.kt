package org.inner.circle.survey.adapter.repository

import org.inner.circle.survey.domain.QuestionOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionOptionRepository : JpaRepository<QuestionOption, Long>
