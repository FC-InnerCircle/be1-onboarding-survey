package org.example.survey.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.survey.domain.enums.InputType

@Entity
@Table(name = "question")
class Question(
    questionId: Long = 0,
    name: String = "",
    description: String = "",
    inputType: InputType,
    required: Boolean,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    var form: Form? = null

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val options: MutableList<Option> = mutableListOf()

    var name: String = name
        protected set

    var description: String = description
        protected set

    @Enumerated(EnumType.STRING)
    var inputType: InputType = inputType
        protected set
    var required: Boolean = required
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var questionId: Long = questionId
        protected set

    fun addOption(option: Option) {
        this.options.add(option)
        option.question = this
    }

    fun updateQuestion(question: Question) {
        this.name = question.name
        this.description = question.description
        this.inputType = question.inputType
        this.required = question.required
        question.options.forEach { option ->
            options
                .find { it == option }
                ?.updateOption(option)
                ?: addOption(option)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Question) return false

        if (questionId != other.questionId) return false

        return true
    }

    override fun hashCode(): Int = questionId.hashCode()
}
