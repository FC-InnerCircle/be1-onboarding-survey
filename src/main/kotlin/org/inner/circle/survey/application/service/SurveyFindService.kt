package org.inner.circle.survey.application.service

import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.presentation.dto.SurveyFindResponse.SurveyResponse
import org.springframework.stereotype.Service

@Service
class SurveyFindService(
    private val survetStorage: SurveyStorage
) {

    fun findSurvey(
        surveyId: Long
    ):SurveyResponse {
        return survetStorage.getSurveyWithQuestion(surveyId)
    }
}