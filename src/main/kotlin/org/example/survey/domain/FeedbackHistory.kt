package org.example.survey.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "feedback_history")
class FeedbackHistory(
    formId: Long,
    questions: String? = null,
    answers: String? = null,
) {
    @Column(name = "form_id")
    val formId: Long = formId

    @Column(name = "questions", columnDefinition = "TEXT")
    val questions: String? = questions

    @Column(name = "answers", columnDefinition = "TEXT")
    @Lob
    val answers: String? = answers

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_history_id")
    val feedbackHistoryId: Long = 0
}
