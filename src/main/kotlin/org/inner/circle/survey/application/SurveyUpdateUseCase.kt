package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyRequest.UpdateSurvey

interface SurveyUpdateUseCase {
    fun updateSurvey(updateSurveyRequest: UpdateSurvey)
}
