package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyRequest.CreateSurvey
import org.inner.circle.survey.adapter.dto.SurveyRequest.UpdateSurvey
import org.inner.circle.survey.domain.Question
import org.inner.circle.survey.domain.Survey

interface SurveyUseCase {
    fun save(createSurvey: CreateSurvey)

    fun save(updateSurvey: UpdateSurvey)

    fun getSurvey(surveyId: Long): Survey

    fun getQuestions(questionId: Long): Question
}
