package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.entity.Answer;
import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.Response;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.ResponseRequest;
import com.fastcampus.innercircle.survey_api.domain.response.SurveyResponse;
import com.fastcampus.innercircle.survey_api.repository.ResponseRepository;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final SurveyFormRepository surveyFormRepository;

    public ResponseService(ResponseRepository responseRepository, SurveyFormRepository surveyFormRepository) {
        this.responseRepository = responseRepository;
        this.surveyFormRepository = surveyFormRepository;
    }

    @Transactional
    public void submitResponse(Long formId, ResponseRequest responseRequest) {
        SurveyForm surveyForm = surveyFormRepository.findById(formId).orElseThrow();

        Response response = new Response();
        response.setFormId(formId);

        List<Answer> answers = responseRequest.getAnswers().stream()
                .map(answerRequest -> {
                    Question question = findQuestionById(surveyForm.getQuestions(), answerRequest.getQuestionId());

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

        responseRepository.save(response);
    }

    public List<SurveyResponse> getResponses(Long formId) {

        List<Response> responses = responseRepository.findByFormId(formId);
        return responses.stream().map(SurveyResponse::of).toList();
    }

    private Question findQuestionById(List<Question> questions, Long id) {
        return questions.stream()
                .filter(question -> question.getQuestionId() != null && question.getQuestionId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
