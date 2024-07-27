package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.QuestionRequest;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyFormService {

    private final SurveyFormRepository surveyFormRepository;

    public SurveyFormService(SurveyFormRepository surveyFormRepository) {
        this.surveyFormRepository = surveyFormRepository;
    }

    @Transactional
    public SurveyForm saveSurveyForm(SurveyFormRequest surveyFormRequest) {
        SurveyForm surveyForm = new SurveyForm(surveyFormRequest.getTitle(), surveyFormRequest.getDescription());

        List<Question> questions = surveyFormRequest.getQuestions().stream()
                .map(Question::of)
                .collect(Collectors.toList());
        surveyForm.addQuestions(questions);

        SurveyForm result = surveyFormRepository.save(surveyForm);

        return result;
    }

    @Transactional
    public SurveyForm updateSurveyForm(Long formId, SurveyFormRequest surveyFormRequest) {
        SurveyForm surveyForm = surveyFormRepository.findById(formId).orElseThrow();
        surveyForm.setTitle(surveyFormRequest.getTitle());
        surveyForm.setDescription(surveyFormRequest.getDescription());

        for (Question question : surveyForm.getQuestions()) {
            QuestionRequest questionRequest = findQuestionRequestById(surveyFormRequest.getQuestions(), question.getQuestionId());

            if (questionRequest != null) {
                // 변경
                question.setTitle(questionRequest.getTitle());
                question.setDescription(questionRequest.getDescription());
                question.setType(questionRequest.getQuestionType());
                question.setRequired(questionRequest.isRequired());
                question.setOptions(questionRequest.getOptions());
                question.setUpdatedAt(LocalDateTime.now());
            } else {
                // 삭제
                question.setRemoved(true);
                question.setRemovedAt(LocalDateTime.now());
            }
        }

        for (QuestionRequest questionRequest : surveyFormRequest.getQuestions()) {
            if (questionRequest.getId() == null) {
                // 추가
                Question question = Question.of(questionRequest);
                surveyForm.addQuestion(question);
            }
        }

        surveyForm.setUpdatedAt(LocalDateTime.now());
        SurveyForm result = surveyFormRepository.save(surveyForm);
        return result;
    }

    private QuestionRequest findQuestionRequestById(List<QuestionRequest> requests, Long id) {
        return requests.stream()
                .filter(questionRequest -> questionRequest.getId() != null && questionRequest.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
