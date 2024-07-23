@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.innercircle.surveydomain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "submissions")
data class Submission(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @ManyToOne @JoinColumn(name = "form_id", nullable = false)
    val form: Form,
    @Column(nullable = false)
    val submittedAt: LocalDateTime,
    @Column(nullable = false, length = 255)
    val respondentInfo: String,
)
