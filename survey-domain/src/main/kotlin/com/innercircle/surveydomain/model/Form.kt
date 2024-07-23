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
    val createdAt: LocalDateTime,
    @Column(nullable = false)
    val updatedAt: LocalDateTime,
    @Column(nullable = false)
    val isActive: Boolean,
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], orphanRemoval = true)
    val questions: List<Question> = mutableListOf(),
)
