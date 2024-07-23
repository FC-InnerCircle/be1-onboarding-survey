@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*

@Entity
@Table(name = "question_options")
data class QuestionOption(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne @JoinColumn(name = "question_id", nullable = false)
    val question: Question,
    @Column(nullable = false, length = 255)
    val optionText: String,
    @Column(nullable = false)
    val optionOrder: Int,
)
