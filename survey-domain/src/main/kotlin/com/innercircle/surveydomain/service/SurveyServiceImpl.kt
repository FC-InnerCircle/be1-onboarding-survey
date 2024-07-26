package com.innercircle.surveydomain.service

import com.innercircle.surveycommon.dto.request.CreateFormRequest
import com.innercircle.surveycommon.dto.request.FormSubmissionRequest
import com.innercircle.surveycommon.dto.response.*
import com.innercircle.surveycommon.exception.InvalidInputException
import com.innercircle.surveydomain.model.*
import com.innercircle.surveydomain.repository.FormRepository
import com.innercircle.surveydomain.repository.ResponseRepository
import com.innercircle.surveydomain.repository.SubmissionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SurveyServiceImpl : SurveyService {
    @Autowired
    private lateinit var formRepository: FormRepository

    @Autowired
    private lateinit var submissionRepository: SubmissionRepository

    @Autowired
    private lateinit var responseRepository: ResponseRepository

    override fun createForm(request: CreateFormRequest): CreateFormResponse {
        /**
         * 1. 입력값 검증
         * 2. 버전 정보는 해당 타이틀의 최신 버전을 조회하여 없으면 최초 1, 아니면 +1 진행
         * 3. 버전 정보를 넣고 입력값을 받아 h2 데이터베이스에 저장
         * 4. 저장된 데이터를 조회하여 FormDto로 변환하여 반환
         */
        if (request.title.isBlank()) {
            throw InvalidInputException("제목은 비워둘 수 없습니다")
        }
        if (request.description.isBlank()) {
            throw InvalidInputException("설명은 비워둘 수 없습니다")
        }
        if (request.questions.isEmpty()) {
            throw InvalidInputException("질문은 비워둘 수 없습니다")
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
            if (q == null) {
                throw InvalidInputException("질문 항목은 null일 수 없습니다")
            }

            val question =
                Question(
                    form = form,
                    formVersion = version,
                    questionText = q.questionText,
                    questionType = q.questionType,
                    questionOrder = q.questionOrder,
                    isRequired = q.isRequired,
                    additionalConfig = q.additionalConfig.toString(),
                    questionOptions = emptyList(),
                )

            val questionOptions =
                q.questionOptions?.map { option ->
                    QuestionOption(
                        question = question,
                        optionText = option.optionText,
                        optionOrder = option.optionOrder,
                    )
                } ?: emptyList()

            question.questionOptions = questionOptions

            form.addQuestion(question)
        }

        val savedForm = formRepository.save(form) ?: throw InvalidInputException("폼 저장에 실패했습니다")

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

    override fun submitForm(
        forms_id: Long,
        request: FormSubmissionRequest,
    ): FormSubmissionResponse {
        // 1. formId로 Form을 조회
        // 2. Form이 없으면 InvalidInputException을 발생
        // 3. Question이 있으면 요청값 Response 저장

        // forms_id null 체크
        if (forms_id == null) {
            throw InvalidInputException("폼 ID는 비워둘 수 없습니다")
        }

        // respondent_info가 없으면 InvalidInputException을 발생
        if (request.respondent_info == null) {
            throw InvalidInputException("응답자 정보는 비워둘 수 없습니다")
        }

        val form = formRepository.findById(forms_id.toInt()).orElseThrow { InvalidInputException("폼을 찾을 수 없습니다") }

        // Submission이 이미 존재하는지 확인
        val existingSubmission = submissionRepository.findByFormIdAndRespondentInfo(forms_id, request.respondent_info)

        // 이미 Submission이 존재하면 InvalidInputException을 발생
        if (existingSubmission != null) {
            throw InvalidInputException("이미 응답을 제출했습니다")
        }

        // Submission이 없으면 Submission을 생성
        val submission =
            submissionRepository.save(
                Submission(
                    form = form,
                    formVersion = form.version,
                    submittedAt = LocalDateTime.now(),
                    respondentInfo = request.respondent_info,
                    responses = emptyList(),
                ),
            )

        // Question이 없으면 InvalidInputException을 발생
        if (form.questions.isEmpty()) {
            throw InvalidInputException("질문이 없습니다")
        }

        // Question이 있으면 요청값 Response 저장
        request.responses.forEach { response ->
            val question =
                form.questions.find { it.id == response.question_id.toInt() }
                    ?: run {
                        submissionRepository.delete(submission)
                        throw InvalidInputException("질문을 찾을 수 없습니다")
                    }

            val questionOption =
                if (response.question_option_id != null) {
                    question.questionOptions.find { it.id == response.question_option_id!!.toInt() }
                        ?: run {
                            submissionRepository.delete(submission)
                            throw InvalidInputException("질문 옵션을 찾을 수 없습니다")
                        }
                } else {
                    null
                }

            responseRepository.save(
                Response(
                    submission = submission,
                    question = question,
                    questionOption = questionOption,
                    responseData = response.response_data.toString(),
                ),
            )
        }

        // 응답 반환
        return FormSubmissionResponse(
            id = submission.id.toLong(),
            form_id = submission.form.id.toLong(),
            form_version = submission.formVersion,
            submitted_at = submission.submittedAt.toString(),
            respondent_info = submission.respondentInfo.toString(),
        )
    }
}
