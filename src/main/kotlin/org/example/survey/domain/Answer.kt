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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    val response: Response,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: Question,
    val content: String? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Long = 0,
)
