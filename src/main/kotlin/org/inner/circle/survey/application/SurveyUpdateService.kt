package org.inner.circle.survey.application

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.adapter.dto.SurveyRequest.UpdateSurvey
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SurveyUpdateService(
    private val surveyUseCase: SurveyUseCase,
) : SurveyUpdateUseCase {
    private val log = KotlinLogging.logger {}

    override fun updateSurvey(updateSurvey: UpdateSurvey) {
        log.info { "Updating survey with id: ${updateSurvey.id}" }
        val survey = surveyUseCase.getSurvey(updateSurvey.id)
        updateSurvey.title?.let { survey.title = it }
        updateSurvey.description?.let { survey.description = it }
        updateSurvey.questions?.let {
            it.forEach { questionRequest ->
                questionRequest.id?.let {
                    val question = surveyUseCase.getQuestions(it)
                }
            }
        }
    }
}
