package org.inner.circle.survey.application

import io.github.oshai.kotlinlogging.KotlinLogging
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.UpdateSurvey
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SurveyUpdateService(
    private val surveyPort: SurveyPort,
) : SurveyUpdateUseCase {
    private val log = KotlinLogging.logger {}

    override fun updateSurvey(updateSurvey: UpdateSurvey) {
        log.info { "Updating survey with id: ${updateSurvey.id}" }
        val survey = surveyPort.getSurvey(updateSurvey.id)
        surveyPort.updateSurvey(updateSurvey, survey)
        updateSurvey.questions?.let {
            it.forEach { questionRequest ->
                surveyPort.updateQuestion(questionRequest, surveyPort.getQuestions(questionRequest.id))
                questionRequest.options?.let { options ->
                    options.forEach { optionRequest ->
                        surveyPort.updateQuestionOption(optionRequest, surveyPort.getOptions(optionRequest.id))
                    }
                }
            }
        }
    }
}
