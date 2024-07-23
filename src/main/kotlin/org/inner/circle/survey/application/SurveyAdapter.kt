package org.inner.circle.survey.application

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.adapter.dto.SurveyRequest
import org.inner.circle.survey.adapter.dto.SurveyRequest.CreateSurvey
import org.inner.circle.survey.adapter.repository.QuestionOptionRepository
import org.inner.circle.survey.adapter.repository.QuestionRepository
import org.inner.circle.survey.adapter.repository.SurveyRepository
import org.inner.circle.survey.domain.Question
import org.inner.circle.survey.domain.Survey
import org.springframework.stereotype.Component

@Component
class SurveyAdapter(
    private val surveyRepository: SurveyRepository,
    private val questionRepository: QuestionRepository,
    private val questionOptionRepository: QuestionOptionRepository,
) : SurveyUseCase {
    private val log = KotlinLogging.logger {}

    override fun save(createSurvey: CreateSurvey) {
        val newSurvey = surveyRepository.save(createSurvey.toSurvey())
        createSurvey.questions.forEach { question ->
            val newQuestion = questionRepository.save(question.toQuestion(newSurvey))
            question.options.forEach { option ->
                questionOptionRepository.save(option.toOption(newQuestion))
            }
        }
    }

    override fun save(updateSurvey: SurveyRequest.UpdateSurvey) {
        TODO("Not yet implemented")
    }

    override fun getSurvey(surveyId: Long): Survey {
        return surveyRepository.findById(surveyId)
            .orElseThrow { IllegalArgumentException("Survey not found") }
    }

    override fun getQuestions(questionId: Long): Question {
        return questionRepository.findById(questionId)
            .orElseThrow { IllegalArgumentException("Question not found") }
    }
}
