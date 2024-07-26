package innerCircle.survey.service

import innerCircle.survey.dto.CreateSurveyRequest
import innerCircle.survey.dto.UpdateSurveyRequest
import innerCircle.survey.entity.*
import innerCircle.survey.repository.SurveyQuestionOptionRepository
import innerCircle.survey.repository.SurveyQuestionRepository
import innerCircle.survey.repository.SurveyRepository
import innerCircle.survey.repository.SurveyVersionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SurveyService(
    private val surveyRepository: SurveyRepository,
    private val surveyVersionRepository: SurveyVersionRepository,
    private val surveyQuestionRepository: SurveyQuestionRepository,
    private val surveyQuestionOptionRepository: SurveyQuestionOptionRepository
) {

    @Transactional
    fun createSurvey(request: CreateSurveyRequest) {
        val survey = createSurveyEntity(request)
        surveyRepository.save(survey)

        val surveyVersion = createSurveyVersionEntity(survey)
        surveyVersionRepository.save(surveyVersion)

        val surveyQuestions = createSurveyQuestions(request, surveyVersion)
        surveyQuestionRepository.saveAll(surveyQuestions)

        val surveyQuestionOptions = createSurveyQuestionOptions(request, surveyQuestions)
        if (surveyQuestionOptions.isNotEmpty()) {
            surveyQuestionOptionRepository.saveAll(surveyQuestionOptions)
        }
    }

    @Transactional
    fun addQuestions(surveyId: Long, request: UpdateSurveyRequest) {
        val survey = getSurveyById(surveyId)
        val currentVersion = getCurrentSurveyVersion(survey)
        val newSurveyVersion = createSurveyVersionEntity(survey)
        surveyVersionRepository.save(newSurveyVersion)

        val existingQuestions = surveyQuestionRepository.findBySurveyVersion(currentVersion)
        val (newQuestions, updatedQuestions) = processQuestions(request, existingQuestions, newSurveyVersion)

        surveyQuestionRepository.saveAll(newQuestions)
        surveyQuestionRepository.saveAll(updatedQuestions)

        val allQuestions = newQuestions + updatedQuestions
        val surveyQuestionOptions = createSurveyQuestionOptionsFromQuestions(request, allQuestions)
        if (surveyQuestionOptions.isNotEmpty()) {
            surveyQuestionOptionRepository.saveAll(surveyQuestionOptions)
        }
    }

    @Transactional
    fun updateQuestions(surveyId: Long, request: UpdateSurveyRequest) {
        val survey = getSurveyById(surveyId)
        val currentVersion = getCurrentSurveyVersion(survey)
        val newSurveyVersion = createSurveyVersionEntity(survey)
        surveyVersionRepository.save(newSurveyVersion)

        val existingQuestions = surveyQuestionRepository.findBySurveyVersion(currentVersion)
        val (newQuestions, updatedQuestions) = processQuestions(request, existingQuestions, newSurveyVersion)

        surveyQuestionRepository.saveAll(newQuestions)
        surveyQuestionRepository.saveAll(updatedQuestions)

        val deletedQuestionIds = existingQuestions.map { it.id!! }.toSet() - newQuestions.map { it.id!! }.toSet() - updatedQuestions.map { it.id!! }.toSet()
        if (deletedQuestionIds.isNotEmpty()) {
            surveyQuestionRepository.markAsDeleted(deletedQuestionIds.toList(), LocalDateTime.now())
        }

        val allQuestions = newQuestions + updatedQuestions
        val surveyQuestionOptions = createSurveyQuestionOptionsFromQuestions(request, allQuestions)
        if (surveyQuestionOptions.isNotEmpty()) {
            surveyQuestionOptionRepository.saveAll(surveyQuestionOptions)
        }
    }

    @Transactional
    fun deleteSingleQuestion(surveyId: Long, questionId: Long) {
        val survey = getSurveyById(surveyId)
        val currentVersion = getCurrentSurveyVersion(survey)
        val newSurveyVersion = createSurveyVersionEntity(survey)
        surveyVersionRepository.save(newSurveyVersion)

        markQuestionAsDeleted(questionId)

        val remainingQuestions = surveyQuestionRepository.findBySurveyVersion(currentVersion)
        surveyQuestionRepository.saveAll(remainingQuestions)

        val remainingOptions = remainingQuestions.flatMap { it.createOptions(emptyList()) }
        if (remainingOptions.isNotEmpty()) {
            surveyQuestionOptionRepository.saveAll(remainingOptions)
        }
    }

    @Transactional
    fun deleteMultipleQuestions(surveyId: Long, questionIds: List<Long>) {
        val survey = getSurveyById(surveyId)
        val currentVersion = getCurrentSurveyVersion(survey)
        val newSurveyVersion = createSurveyVersionEntity(survey)
        surveyVersionRepository.save(newSurveyVersion)

        markQuestionsAsDeleted(questionIds)

        val remainingQuestions = surveyQuestionRepository.findBySurveyVersion(currentVersion)
        surveyQuestionRepository.saveAll(remainingQuestions)

        val remainingOptions = remainingQuestions.flatMap { it.createOptions(emptyList()) }
        if (remainingOptions.isNotEmpty()) {
            surveyQuestionOptionRepository.saveAll(remainingOptions)
        }
    }


    private fun createSurveyEntity(request: CreateSurveyRequest): Survey {
        return Survey.create(request.name, request.description)
    }

    private fun createSurveyVersionEntity(survey: Survey): SurveyVersion {
        return SurveyVersion.create(survey)
    }

    private fun createSurveyQuestions(request: CreateSurveyRequest, surveyVersion: SurveyVersion): List<SurveyQuestion> {
        return request.questions.map { questionRequest ->
            SurveyQuestion.create(
                questionRequest.name,
                questionRequest.description,
                questionRequest.questionType,
                questionRequest.isRequired,
                surveyVersion
            )
        }
    }

    private fun createSurveyQuestionOptions(request: CreateSurveyRequest, surveyQuestions: List<SurveyQuestion>): List<SurveyQuestionOption> {
        return surveyQuestions.flatMapIndexed { index, surveyQuestion ->
            val questionRequest = request.questions[index]
            surveyQuestion.createOptions(questionRequest.options)
        }
    }

    private fun createSurveyQuestionOptionsFromQuestions(request: UpdateSurveyRequest, surveyQuestions: List<SurveyQuestion>): List<SurveyQuestionOption> {
        return surveyQuestions.flatMap { question ->
            val options = request.questions.find { it.id == question.id }?.options ?: emptyList()
            question.createOptions(options)
        }
    }

    private fun markQuestionAsDeleted(questionId: Long) {
        surveyQuestionRepository.markAsDeleted(listOf(questionId), LocalDateTime.now())
    }

    private fun markQuestionsAsDeleted(questionIds: List<Long>) {
        surveyQuestionRepository.markAsDeleted(questionIds, LocalDateTime.now())
    }


    private fun processQuestions(request: UpdateSurveyRequest, existingQuestions: List<SurveyQuestion>, newSurveyVersion: SurveyVersion): Pair<List<SurveyQuestion>, List<SurveyQuestion>> {
        val existingQuestionIds = existingQuestions.map { it.id }.toSet()

        val newQuestions = request.questions.filter { it.id == null }.map { questionRequest ->
            SurveyQuestion.create(
                questionRequest.name,
                questionRequest.description,
                questionRequest.questionType,
                questionRequest.isRequired,
                newSurveyVersion
            )
        }

        val updatedQuestions = request.questions.filter { it.id != null }.mapNotNull { questionRequest ->
            val existingQuestion = existingQuestions.find { it.id == questionRequest.id }
            existingQuestion?.update(
                questionRequest.name,
                questionRequest.description,
                questionRequest.questionType,
                questionRequest.isRequired,
                newSurveyVersion
            )
        }

        return Pair(newQuestions, updatedQuestions)
    }

    private fun getSurveyById(surveyId: Long): Survey {
        return surveyRepository.findById(surveyId).orElseThrow {
            IllegalArgumentException("Survey with ID $surveyId does not exist.")
        }
    }

    private fun getCurrentSurveyVersion(survey: Survey): SurveyVersion {
        return surveyVersionRepository.findBySurvey(survey).lastOrNull()
            ?: throw IllegalStateException("Current survey version not found.")
    }
}
