package org.inner.circle.survey.application.service

import org.inner.circle.survey.domain.storage.AnswerStorage
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.presentation.dto.AnswerCreateRequest
import org.springframework.stereotype.Service

@Service
class AnswerCreateService(
    private val answerStorage: AnswerStorage,
    private val surveyStorage: SurveyStorage,
) {
    fun saveAnswer(createAnswer: AnswerCreateRequest.CreateAnswer) {
        val survey = surveyStorage.getSurvey(createAnswer.surveyId)
        createAnswer.answerArticles.forEach { answerArticle ->
            val question = surveyStorage.getQuestion(answerArticle.questionId, survey)
            answerStorage.save(survey, question, createAnswer)
        }
    }
}
