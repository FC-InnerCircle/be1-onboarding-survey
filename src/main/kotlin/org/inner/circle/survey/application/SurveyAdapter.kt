package org.inner.circle.survey.application

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.adapter.dto.SurveyCreateRequest.CreateSurvey
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.Questions
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.Options
import org.inner.circle.survey.adapter.repository.QuestionOptionRepository
import org.inner.circle.survey.adapter.repository.QuestionRepository
import org.inner.circle.survey.adapter.repository.SurveyRepository
import org.inner.circle.survey.domain.Question
import org.inner.circle.survey.domain.QuestionOption
import org.inner.circle.survey.domain.Survey
import org.springframework.stereotype.Component

@Component
class SurveyAdapter(
    private val surveyRepository: SurveyRepository,
    private val questionRepository: QuestionRepository,
    private val questionOptionRepository: QuestionOptionRepository,
) : SurveyPort {
    private val log = KotlinLogging.logger {}

    override fun save(createSurvey: CreateSurvey) {
        val newSurvey = surveyRepository.save(createSurvey.toSurvey())
        createSurvey.questions.forEach { question ->
            val newQuestion = questionRepository.save(question.toQuestion(newSurvey))
            question.options.forEach { option ->
                questionOptionRepository.save(option.toOption(newQuestion))
            }
        }
        log.info { "Survey created with id: $newSurvey" }
    }

    override fun getSurvey(surveyId: Long): Survey {
        return surveyRepository.findById(surveyId)
            .orElseThrow { IllegalArgumentException("Survey not found") }
    }

    override fun getQuestions(questionId: Long): Question {
        return questionRepository.findById(questionId)
            .orElseThrow { IllegalArgumentException("Question not found") }
    }

    override fun getOptions(optionId: Long): QuestionOption {
        return questionOptionRepository.findById(optionId)
            .orElseThrow { IllegalArgumentException("Option not found") }
    }

    override fun updateSurvey(
        updateSurvey: SurveyUpdateRequest.UpdateSurvey,
        survey: Survey
    ) {
        survey.title = updateSurvey.title ?: survey.title
        survey.description = updateSurvey.description ?: survey.description
        surveyRepository.save(survey)
        log.info { "Survey updated with id: $survey" }
    }

    override fun updateQuestion(
        updateQuestion: Questions,
        question: Question
    ) {
        question.title = updateQuestion.title ?: question.title
        question.type = updateQuestion.type ?: question.type
        question.description = updateQuestion.description ?: question.description
        questionRepository.save(question)
        log.info { "Question updated with id: $question" }
    }

    override fun updateQuestionOption(
        updateSurvey: Options,
        option: QuestionOption
    ) {
        option.content = updateSurvey.content ?: option.content
        option.orderNumber = updateSurvey.orderNumber ?: option.orderNumber
        questionOptionRepository.save(option)
        log.info { "Option updated with id: $option" }
    }


}
