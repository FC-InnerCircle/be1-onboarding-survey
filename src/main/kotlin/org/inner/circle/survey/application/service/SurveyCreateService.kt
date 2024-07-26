package org.inner.circle.survey.application.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.domain.storage.SurveyStorage
import org.inner.circle.survey.presentation.dto.SurveyCreateRequest.CreateSurvey
import org.springframework.stereotype.Service

@Service
class SurveyCreateService(
    private val surveyStorage: SurveyStorage,
) {
    private val log = KotlinLogging.logger {}

    fun saveSurvey(createSurvey: CreateSurvey) {
        log.info { "Creating survey with title: ${createSurvey.title}" }
        surveyStorage.save(createSurvey)
    }
}
