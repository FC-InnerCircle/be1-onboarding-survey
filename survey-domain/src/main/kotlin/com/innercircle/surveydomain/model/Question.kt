@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    val form: Form,
    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    val questionType: QuestionType,
    @Column(name = "question_text", nullable = false)
    val questionText: String,
    @Column(name = "question_order", nullable = false)
    val questionOrder: Int,
    @Column(name = "is_required", nullable = false)
    val isRequired: Boolean,
    @Column(name = "additional_config")
    val additionalConfig: String? = null,
)
