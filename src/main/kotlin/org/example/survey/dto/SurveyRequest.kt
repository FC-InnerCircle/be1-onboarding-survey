package org.example.survey.dto

import org.example.survey.domain.Form
import org.example.survey.domain.Option
import org.example.survey.domain.Question

data class SurveyRequest(
    val name: String,
    val description: String,
    val questions: List<QuestionRequest>,
) {
    fun toFormEntity(): Form {
        val form =
            Form(
                name = this.name,
                description = this.description,
            )

        this.questions.forEach { questionRequest ->
            val question =
                Question(
                    form = form,
                    name = questionRequest.name,
                    description = questionRequest.description,
                    inputTypes = questionRequest.inputTypes,
                    required = questionRequest.required,
                )

            questionRequest.options.forEach { optionRequest ->
                val option =
                    Option(
                        question = question,
                        content = optionRequest.content,
                        seq = optionRequest.seq,
                    )
                question.options.add(option)
            }

            form.questions.add(question)
        }
        return form
    }
}
