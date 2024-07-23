@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*

@Entity
@Table(name = "responses")
data class Response(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne @JoinColumn(name = "question_id", nullable = false)
    val question: Question,
    @ManyToOne @JoinColumn(name = "submission_id", nullable = false)
    val submission: Submission,
    @Column(columnDefinition = "TEXT")
    val responseData: String? = null,
)
