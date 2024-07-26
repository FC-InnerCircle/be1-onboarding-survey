package org.inner.circle.survey.domain.storage

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.application.service.SurveyFindService
import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.QuestionOption
import org.inner.circle.survey.domain.entity.Survey
import org.inner.circle.survey.infrastructure.repository.QuestionOptionRepository
import org.inner.circle.survey.infrastructure.repository.QuestionRepository
import org.inner.circle.survey.infrastructure.repository.SurveyRepository
import org.inner.circle.survey.presentation.dto.SurveyCreateRequest.CreateSurvey
import org.inner.circle.survey.presentation.dto.SurveyFindResponse.OptionResponse
import org.inner.circle.survey.presentation.dto.SurveyFindResponse.QuestionResponse
import org.inner.circle.survey.presentation.dto.SurveyFindResponse.SurveyResponse
import org.inner.circle.survey.presentation.dto.SurveyUpdateRequest
import org.inner.circle.survey.presentation.dto.SurveyUpdateRequest.Options
import org.inner.circle.survey.presentation.dto.SurveyUpdateRequest.Questions
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SurveyStorage(
    private val surveyRepository: SurveyRepository,
    private val questionRepository: QuestionRepository,
    private val questionOptionRepository: QuestionOptionRepository,
) {
    private val log = KotlinLogging.logger {}

    @Transactional
    fun save(createSurvey: CreateSurvey) {
        val newSurvey = surveyRepository.save(createSurvey.toSurvey())
        createSurvey.questions.forEach { question ->
            val newQuestion = questionRepository.save(question.toQuestion(newSurvey))
            question.options.forEach { option ->
                questionOptionRepository.save(option.toOption(newQuestion))
            }
        }
        log.info { "Survey created with id: $newSurvey" }
    }

    @Transactional(readOnly = true)
    fun getSurvey(surveyId: Long): Survey {
        return surveyRepository.findById(surveyId)
            .orElseThrow { IllegalArgumentException("Survey not found") }
    }

    @Transactional(readOnly = true)
    fun getSurveyWithQuestion(surveyId: Long): SurveyResponse {
        val survey = getSurvey(surveyId)
        val questions = questionRepository.findBySurvey(survey)
        return SurveyResponse(
            id = survey.id,
            title = survey.title,
            description = survey.description,
            questions = questions.map { question ->
                val options = questionOptionRepository.findByQuestion(question).map { option ->
                    OptionResponse(
                        id = option.id,
                        content = option.content,
                        orderNumber = option.orderNumber,
                    )
                }
                QuestionResponse(
                    id = question.id,
                    title = question.title,
                    type = question.type,
                    description = question.description,
                    options = options,
                    isRequired = question.requiredFlag,
                    orderNumber = question.orderNumber,
                )
            }
        )
    }

    @Transactional(readOnly = true)
    fun getQuestion(
        questionId: Long,
        survey: Survey,
    ): Question {
        return questionRepository.findByIdAndSurvey(questionId, survey)
            ?: throw IllegalArgumentException("Question not found")
    }

    @Transactional(readOnly = true)
    fun getOptions(optionId: Long): QuestionOption {
        return questionOptionRepository.findById(optionId)
            .orElseThrow { IllegalArgumentException("Option not found") }
    }

    @Transactional
    fun updateSurvey(updateSurvey: SurveyUpdateRequest.UpdateSurvey) {
        val survey = getSurvey(updateSurvey.id)
        survey.title = updateSurvey.title ?: survey.title
        survey.description = updateSurvey.description ?: survey.description

        updateSurvey.questions?.forEach { questionRequest ->
            val questionOption = getQuestion(questionRequest.id, survey)
            updateQuestion(questionRequest, questionOption)
            questionRequest.options?.forEach { optionRequest ->
                val option = getOptions(optionRequest.id)
                updateQuestionOption(optionRequest, option)
            }
        }

        surveyRepository.save(survey)
        log.info { "Survey  updated with id: $survey" }
    }

    @Transactional
    fun updateQuestion(
        updateQuestion: Questions,
        question: Question,
    ) {
        question.title = updateQuestion.title ?: question.title
        question.type = updateQuestion.type ?: question.type
        question.description = updateQuestion.description ?: question.description
        questionRepository.save(question)
        log.info { "Question updated with id: $question" }
    }

    @Transactional
    fun updateQuestionOption(
        updateSurvey: Options,
        option: QuestionOption,
    ) {
        option.content = updateSurvey.content ?: option.content
        option.orderNumber = updateSurvey.orderNumber ?: option.orderNumber
        questionOptionRepository.save(option)
        log.info { "Option updated with id: $option" }
    }
}
