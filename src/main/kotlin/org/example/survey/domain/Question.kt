package org.example.survey.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "question")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val questionId: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    val form: Form,
    val name: String? = null,
    val description: String? = null,
    val inputTypes: String? = null,
    val required: Boolean? = null,
    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val options: MutableList<Option> = mutableListOf(),
    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val answers: MutableList<Answer> = mutableListOf(),
)
