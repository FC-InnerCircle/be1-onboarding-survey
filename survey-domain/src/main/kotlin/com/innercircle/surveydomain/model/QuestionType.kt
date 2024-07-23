@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*

@Entity
@Table(name = "question_types")
data class QuestionType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @Column(nullable = false, length = 50)
    val typeName: String,
    @Column(columnDefinition = "TEXT")
    val configSchema: String? = null,
)
