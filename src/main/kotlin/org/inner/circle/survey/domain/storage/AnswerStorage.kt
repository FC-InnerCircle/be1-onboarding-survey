package org.inner.circle.survey.domain.storage

import org.inner.circle.survey.domain.entity.Question
import org.inner.circle.survey.domain.entity.Survey
import org.inner.circle.survey.infrastructure.repository.AnswerArticleRepository
import org.inner.circle.survey.infrastructure.repository.AnswerRepository
import org.inner.circle.survey.presentation.dto.AnswerCreateRequest.CreateAnswer
import org.inner.circle.survey.presentation.dto.AnswerFindRequest.FindAnswer
import org.inner.circle.survey.presentation.dto.AnswerFindResponse.AnswerResponse
import org.inner.circle.survey.presentation.dto.AnswerFindResponse.QuestionResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AnswerStorage(
    private val answerRepository: AnswerRepository,
    private val answerArticleRepository: AnswerArticleRepository,
) {
    @Transactional
    fun save(
        survey: Survey,
        question: Question,
        createAnswer: CreateAnswer,
    ) {
        val answer = createAnswer.toAnswer(survey)
        val saveAnswer = answerRepository.save(answer)
        createAnswer.answerArticles.forEach { answerArticle ->
            val answerArticle = answerArticle.toAnswerArticle(saveAnswer, question)
            answerArticleRepository.save(answerArticle)
        }
    }

    @Transactional(readOnly = true)
    fun findAnswers(
        survey: Survey,
        findAnswer: FindAnswer,
    ): AnswerResponse {
        val answers = answerRepository.findAllBySurvey(survey) ?: throw Exception("No answers found for survey ${survey.id}")

        val filteredAnswers =
            answers.filter { answer ->
                val answerArticles = answerArticleRepository.findAllByAnswer(answer)
                val matchesTitle =
                    findAnswer.surveyTitle?.let { title ->
                        answerArticles.any { article -> article.question.title.contains(title) }
                    } ?: true

                val matchesContent =
                    findAnswer.answerContent?.let { content ->
                        answerArticles.any { article -> content.contains(article.content) }
                    } ?: true

                matchesTitle && matchesContent
            }

        val questionResponses =
            filteredAnswers.flatMap { answer ->
                answerArticleRepository.findAllByAnswer(answer).map { article ->
                    QuestionResponse(
                        questionId = article.question.id,
                        title = article.question.title,
                        type = article.question.type,
                        description = article.question.description,
                        content = article.content,
                        orderNumber = article.question.orderNumber,
                    )
                }
            }

        return AnswerResponse(
            surveyId = survey.id,
            surveyTitle = survey.title,
            description = survey.description,
            writer = survey.writer,
            questions = questionResponses,
        )
    }
}
