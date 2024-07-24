package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.UpdateSurvey


interface SurveyUpdateUseCase {
    fun updateSurvey(updateSurveyRequest: UpdateSurvey)
}
