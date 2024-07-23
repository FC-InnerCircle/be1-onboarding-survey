package com.innercircle.surveyapi.api

class SurveyApiControllerImpl : SurveyApiController {
    override fun createSurvey(): String = "createSurvey"

    override fun updateSurvey(): String = "updateSurvey"

    override fun submitSurveyResponse(): String = "submitSurveyResponse"

    override fun getSurveyResponse(): String = "getSurveyResponse"
}
