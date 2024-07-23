package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyRequest

interface SurveyCreateUseCase {
    fun saveSurvey(createSurvey: SurveyRequest.CreateSurvey)
}
