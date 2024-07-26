package org.example.survey.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "feedback")
class Feedback(
    formId: Long = 0,
    answers: List<Answer> = listOf(),
) {
    val feedbackAt: LocalDateTime = LocalDateTime.now()

    var formId: Long = formId
        protected set

    @OneToMany(mappedBy = "feedback", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val answers: MutableList<Answer> = answers.toMutableList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val feedbackId: Long = 0

    fun addAnswer(answer: Answer) {
        this.answers.add(answer)
        answer.feedback = this
    }
}
