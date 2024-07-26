package org.inner.circle.survey.infrastructure.repository

import org.inner.circle.survey.domain.entity.Survey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyRepository : JpaRepository<Survey, Long>
