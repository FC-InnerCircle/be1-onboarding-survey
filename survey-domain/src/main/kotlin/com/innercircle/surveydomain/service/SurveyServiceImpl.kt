package com.innercircle.surveydomain.service

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.response.CreateFormResponse
import com.innercircle.surveycommon.dto.response.FormDto
import com.innercircle.surveycommon.dto.response.QuestionDto
import com.innercircle.surveycommon.dto.response.QuestionOptionDto
import com.innercircle.surveydomain.model.Form
import com.innercircle.surveydomain.model.Question
import com.innercircle.surveydomain.model.QuestionOption
import com.innercircle.surveydomain.repository.FormRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SurveyServiceImpl : SurveyService {
    @Autowired
    private lateinit var formRepository: FormRepository

    override fun createForm(request: CreateFormRequest): CreateFormResponse {
        /**
         * 1. 입력값 검증
         * 2. 버전 정보는 해당 타이틀의 최신 버전을 조회하여 없으면 최초 1, 아니면 +1 진행
         * 3. 버전 정보를 넣고 입력값을 받아 h2 데이터베이스에 저장
         * 4. 저장된 데이터를 조회하여 FormDto로 변환하여 반환
         */
        if (request.title.isBlank()) {
            throw IllegalArgumentException("Title must not be blank")
        }
        if (request.description.isBlank()) {
            throw IllegalArgumentException("Description must not be blank")
        }
        if (request.questions.isEmpty()) {
            throw IllegalArgumentException("Questions must not be empty")
        }

        val latestForm = formRepository.findTopByTitleOrderByVersionDesc(request.title)
        val version = latestForm?.version?.plus(1) ?: 1

        val form =
            Form(
                title = request.title,
                description = request.description,
                isActive = request.isActive ?: false,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                version = version,
            )

        request.questions.forEachIndexed { index, q ->
            val question =
                Question(
                    form = form,
                    formVersion = version,
                    questionText = q.questionText,
                    questionType = q.questionType,
                    questionOrder = q.questionOrder,
                    isRequired = q.isRequired,
                    additionalConfig = q.additionalConfig.toString(),
                    questionOptions = emptyList(), // Initialize with an empty list
                )

            val questionOptions =
                q.questionOptions?.map { option ->
                    QuestionOption(
                        question = question, // Set the question reference
                        optionText = option.optionText,
                        optionOrder = option.optionOrder,
                    )
                } ?: emptyList()

            question.questionOptions = questionOptions // Set the question options

            form.addQuestion(question)
        }

        val savedForm = formRepository.save(form)

        val formDto =
            FormDto(
                id = savedForm.id,
                title = savedForm.title,
                description = savedForm.description.toString(),
                createdAt = savedForm.createdAt.toString(),
                updatedAt = savedForm.updatedAt.toString(),
                isActive = savedForm.isActive,
                version = savedForm.version,
            )

        val questionDtos =
            savedForm.questions.map { question ->
                QuestionDto(
                    id = question.id,
                    formId = question.form.id,
                    formVersion = question.formVersion,
                    questionText = question.questionText,
                    questionType = question.questionType,
                    questionOrder = question.questionOrder,
                    isRequired = question.isRequired,
                    additionalConfig = question.additionalConfig,
                    questionOptions =
                        question.questionOptions.map { option ->
                            QuestionOptionDto(
                                id = option.id,
                                questionId = option.question.id,
                                optionText = option.optionText,
                                optionOrder = option.optionOrder,
                            )
                        },
                )
            }

        return CreateFormResponse(
            form = formDto,
            questions = questionDtos,
        )
    }
}
