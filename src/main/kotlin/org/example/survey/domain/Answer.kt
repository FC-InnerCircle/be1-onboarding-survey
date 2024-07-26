package org.example.survey.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "answer")
class Answer(
    content: String,
    questionId: Long,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    var feedback: Feedback? = null

    val questionId: Long = questionId

    val content: String? = content

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Long = 0

    override fun toString(): String = "Answer(questionId=$questionId, content=$content, answerId=$answerId)"
}
