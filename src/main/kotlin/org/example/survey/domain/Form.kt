package org.example.survey.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "form")
class Form(
    val name: String? = null,
    val description: String? = null,
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val questions: MutableList<Question> = mutableListOf(),
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val responses: MutableList<Response> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val formId: Long = 0,
)
