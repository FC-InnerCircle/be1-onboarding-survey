package innerCircle.survey.service

import innerCircle.survey.common.QuestionType
import innerCircle.survey.dto.*
import innerCircle.survey.entity.*
import innerCircle.survey.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SurveyResponseService(
    private val surveyRepository: SurveyRepository,
    private val surveyVersionRepository: SurveyVersionRepository,
    private val surveyQuestionRepository: SurveyQuestionRepository,
    private val surveyResponseRepository: SurveyResponseRepository,
    private val surveyResponseAnswerRepository: SurveyResponseAnswerRepository,
    private val surveyResponseAnswerOptionRepository: SurveyResponseAnswerOptionRepository
) {

    @Transactional
    fun submitSurveyResponse(request: SurveyResponseRequest) {
        val survey = surveyRepository.findById(request.surveyId).orElseThrow {
            IllegalArgumentException("Survey with ID ${request.surveyId} not found.")
        }

        val lastSurvey = surveyVersionRepository.findLatestBySurvey(survey)!!
        val questions = surveyQuestionRepository.findBySurveyVersion(lastSurvey)
        val questionMap = questions.associateBy { it.id }

        // Create SurveyResponse
        val surveyResponse = SurveyResponse(
            surveyVersion = lastSurvey
        )
        surveyResponseRepository.save(surveyResponse)

        // Validate and save responses
        val responsesGroupedByQuestionId = request.responses.groupBy { it.questionId }

        responsesGroupedByQuestionId.forEach { (questionId, responsesForQuestion) ->
            val question = questionMap[questionId]
                ?: throw IllegalArgumentException("Question with ID $questionId not found.")

            when (question.questionType) {
                QuestionType.SINGLE_CHOICE, QuestionType.MULTIPLE_CHOICE -> {
                    val optionValues = responsesForQuestion.map { it.responseValue }
                    val validOptions = question.createOptions(null).map { it.optionValue }
                    optionValues.forEach { responseValue ->
                        if (!validOptions.contains(responseValue)) {
                            throw IllegalArgumentException("Response value $responseValue is not valid for question with ID $questionId.")
                        }
                    }
                    responsesForQuestion.forEach { response ->
                        val surveyResponseAnswerOption = SurveyResponseAnswerOption(
                            response = surveyResponse,
                            question = question,
                            optionValue = response.responseValue
                        )
                        surveyResponseAnswerOptionRepository.save(surveyResponseAnswerOption)
                    }
                }
                else -> {
                    responsesForQuestion.forEach { response ->
                        val surveyResponseAnswer = SurveyResponseAnswer(
                            response = surveyResponse,
                            question = question,
                            answerValue = response.responseValue
                        )
                        surveyResponseAnswerRepository.save(surveyResponseAnswer)
                    }
                }
            }
        }
    }

    fun getSurveyResponses(surveyId: Long, questionName: String?, responseValue: String?): List<SurveyResponseDto> {
        val survey = surveyRepository.findById(surveyId).orElseThrow {
            IllegalArgumentException("Survey with ID ${surveyId} not found.")
        }

        val lastSurvey = surveyVersionRepository.findLatestBySurvey(survey)!!
        val responses = surveyResponseRepository.findBySurveyVersion(lastSurvey)

        return responses.map { response ->
            val answers = surveyResponseAnswerRepository.findByResponse(response).map { answer ->
                val question = answer.question
                val options = if (question.questionType in listOf(QuestionType.SINGLE_CHOICE, QuestionType.MULTIPLE_CHOICE)) {
                    surveyResponseAnswerOptionRepository.findByQuestionAndResponse(question, response)
                        .map { it.optionValue }
                } else {
                    null
                }
                SurveyResponseAnswerDto(
                    questionId = question.id!!,
                    questionName = question.name,
                    answerValue = answer.answerValue,
                    options = options
                )
            }
            SurveyResponseDto(
                id = response.id!!,
                submittedAt = response.submittedAt.toString(),
                answers = answers
            )
        }
    }
}
