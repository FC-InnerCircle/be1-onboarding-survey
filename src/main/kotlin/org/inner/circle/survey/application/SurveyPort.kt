package org.inner.circle.survey.application

import org.inner.circle.survey.adapter.dto.SurveyCreateRequest.CreateSurvey
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.Options
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.Questions
import org.inner.circle.survey.adapter.dto.SurveyUpdateRequest.UpdateSurvey
import org.inner.circle.survey.domain.Question
import org.inner.circle.survey.domain.QuestionOption
import org.inner.circle.survey.domain.Survey

interface SurveyPort {
    fun save(createSurvey: CreateSurvey)

    fun getSurvey(surveyId: Long): Survey

    fun getQuestions(questionId: Long): Question

    fun getOptions(optionId: Long): QuestionOption

    fun updateSurvey(updateSurvey: UpdateSurvey, survey: Survey)

    fun updateQuestion(updateQuestion: Questions, question: Question)

    fun updateQuestionOption(updateSurvey: Options, option: QuestionOption)
}
