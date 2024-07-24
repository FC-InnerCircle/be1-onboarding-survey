package org.inner.circle.survey.application

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.adapter.dto.SurveyCreateRequest.CreateSurvey
import org.springframework.stereotype.Service

@Service
class SurveyCreateService(
    private val surveyPort: SurveyPort,
) : SurveyCreateUseCase {
    private val log = KotlinLogging.logger {}

    override fun saveSurvey(createSurvey: CreateSurvey) {
        log.info { "Creating survey with title: ${createSurvey.title}" }

        surveyPort.save(createSurvey)
    }
}
