package org.inner.circle.survey.application.service

import org.inner.circle.survey.domain.storage.AnswerStorage
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.presentation.dto.AnswerFindRequest.FindAnswer
import org.inner.circle.survey.presentation.dto.AnswerFindResponse.AnswerResponse
import org.springframework.stereotype.Service

@Service
class AnswerFindService(
    private val surveyStorage: SurveyStorage,
    private val answerStorage: AnswerStorage,
) {
    fun findAnswer(findAnswer: FindAnswer): AnswerResponse {
        val survey = surveyStorage.getSurvey(findAnswer.surveyId)
        return answerStorage.findAnswers(survey, findAnswer)
    }
}
