@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "forms")
data class Form(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @Column(nullable = false, length = 255)
    val title: String,
    @Column(columnDefinition = "TEXT")
    val description: String? = null,
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @Column(nullable = false)
    val isActive: Boolean,
    val version: Int,
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var questions: MutableList<Question> = mutableListOf(),
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val submissions: List<Submission> = emptyList(),
) {
    fun addQuestion(question: Question) {
        question.form = this
        questions.add(question)
    }
}
