package org.example.survey.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.survey.domain.enums.InputType
import java.time.LocalDateTime

@Entity
@Table(name = "feedback")
class Feedback(
    formId: Long = 0,
    answers: List<Answer> = listOf(),
) {
    val feedbackAt: LocalDateTime = LocalDateTime.now()

    var formId: Long = formId
        protected set

    @OneToMany(mappedBy = "feedback", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val answers: MutableList<Answer> = answers.toMutableList()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val feedbackId: Long = 0

    fun addAnswer(answer: Answer) {
        this.answers.add(answer)
        answer.feedback = this
    }

    fun checkQuestions(form: Form) {
        form.questions.forEach { checkQuestions(it) }
    }

    private fun checkQuestions(question: Question) {
        val answers = this.answers.filter { it.questionId == question.questionId }

        if (question.inputType == InputType.SINGLE_CHOICE && answers.size > 1) {
            throw IllegalArgumentException("단일 질문의 경우 하나만 선택 되어야합니다. 질문 id: ${question.questionId}")
        }
        if (question.required && answers.isEmpty()) {
            throw IllegalArgumentException("필수 질문의 경우 값이 입력되어야합니다. 질문 id: ${question.questionId}")
        }
    }
}
