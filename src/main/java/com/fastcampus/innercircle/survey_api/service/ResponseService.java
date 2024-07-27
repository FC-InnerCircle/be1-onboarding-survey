package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.entity.Answer;
import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.Response;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.ResponseRequest;
import com.fastcampus.innercircle.survey_api.domain.response.SurveyResponse;
import com.fastcampus.innercircle.survey_api.repository.QuestionRepository;
import com.fastcampus.innercircle.survey_api.repository.ResponseRepository;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final SurveyFormRepository surveyFormRepository;
    private final QuestionRepository questionRepository;

    public ResponseService(ResponseRepository responseRepository, SurveyFormRepository surveyFormRepository, QuestionRepository questionRepository) {
        this.responseRepository = responseRepository;
        this.surveyFormRepository = surveyFormRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Response submitResponse(Long formId, ResponseRequest responseRequest) {
        SurveyForm surveyForm = surveyFormRepository.findById(formId).orElseThrow();

        List<Question> questions = questionRepository.findByFormAndIsRemovedFalse(surveyForm);
        Map<Long, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getQuestionId, question -> question));

        Response response = new Response();
        response.setFormId(formId);

        List<Answer> answers = responseRequest.getAnswers().stream()
                .map(answerRequest -> {
                    Question question = questionMap.get(answerRequest.getQuestionId());
                    if (question == null) {
                        throw new NoSuchElementException();
                    }

                    if (Arrays.asList(QuestionType.SINGLE_CHOICE, QuestionType.MULTIPLE_CHOICE).contains(question.getType())) {
                        if (!validateChoiceAnswer(answerRequest.getContent(), question)) {
                            throw new NoSuchElementException();
                        }
                    }

                    Answer answer = new Answer();
                    answer.setQuestionTitle(question.getTitle());
                    answer.setQuestionDescription(question.getDescription());
                    answer.setQuestionType(question.getType());
                    answer.setRequired(question.isRequired());
                    answer.setContent(answerRequest.getContent());
                    answer.setResponse(response);
                    return answer;
                })
                .collect(Collectors.toList());

        response.setAnswers(answers);

        return responseRepository.save(response);
    }

    @Transactional
    public List<SurveyResponse> getResponses(Long formId) {

        List<Response> responses = responseRepository.findByFormId(formId);
        return responses.stream().map(SurveyResponse::of).toList();
    }

    private boolean validateChoiceAnswer(String answer, Question question) {
        List<String> options = question.getOptions();

        // 단일 선택
        if (question.getType() == QuestionType.SINGLE_CHOICE) {
            return options.contains(answer);
        }

        // 다중 선택
        if (question.getType() == QuestionType.MULTIPLE_CHOICE) {
            return Arrays.stream(answer.split(","))
                    .allMatch(options::contains);
        }
        return false;
    }
}
