package org.example.survey.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "option")
class Option(
    optionId: Long = 0,
    question: Question,
    content: String,
    seq: Int,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    var question: Question = question

    var content: String = content
    var seq: Int = seq

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var optionId: Long = optionId
        protected set

    fun updateOption(option: Option) {
        this.content = option.content
        this.seq = option.seq
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Option) return false

        if (optionId != other.optionId) return false

        return true
    }

    override fun hashCode(): Int = optionId.hashCode()

    override fun toString(): String = "Option(optionId=$optionId, content='$content', seq=$seq)"
}
