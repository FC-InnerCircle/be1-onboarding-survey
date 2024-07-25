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
@Table(name = "option")
class Option(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: Question,
    val content: String? = null,
    val seq: Int? = 0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val optionId: Long = 0,
)
