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
    var name: String? = null,
    var description: String? = null,
    @OneToMany(mappedBy = "form", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var questions: MutableList<Question> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val formId: Long = 0,
) {
    fun updateForm(updatedForm: Form) {
        this.name = updatedForm.name
        this.description = updatedForm.description
        updatedForm.questions.forEach { question ->
            questions
                .find { it == question }
                ?.updateQuestion(question)
                ?: addQuestion(question)
        }
    }

    fun addQuestion(question: Question) {
        this.questions.add(question)
        question.form = this
    }
}
