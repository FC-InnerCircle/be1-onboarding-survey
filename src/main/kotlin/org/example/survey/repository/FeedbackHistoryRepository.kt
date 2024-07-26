package org.example.survey.repository

import org.example.survey.domain.FeedbackHistory
import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackHistoryRepository : JpaRepository<FeedbackHistory, Long>
