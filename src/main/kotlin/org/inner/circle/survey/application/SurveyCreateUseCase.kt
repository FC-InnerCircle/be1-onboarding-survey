package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyCreateRequest

interface SurveyCreateUseCase {
    fun saveSurvey(createSurvey: SurveyCreateRequest.CreateSurvey)
}
