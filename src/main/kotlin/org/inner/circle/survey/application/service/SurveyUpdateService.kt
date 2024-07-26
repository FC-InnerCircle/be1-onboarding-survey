package org.inner.circle.survey.application.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.presentation.dto.SurveyUpdateRequest.UpdateSurvey
import org.springframework.stereotype.Service

@Service
class SurveyUpdateService(
    private val surveyStorage: SurveyStorage,
) {
    private val log = KotlinLogging.logger {}

    fun updateSurvey(updateSurvey: UpdateSurvey) {
        log.info { "Updating survey with id: ${updateSurvey.id}" }
        surveyStorage.updateSurvey(updateSurvey)
    }
}
