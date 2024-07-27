package com.inner_circle.survey.service;

import com.inner_circle.survey.dto.request.OptionRequest;
import com.inner_circle.survey.dto.request.QuestionRequest;
import com.inner_circle.survey.dto.request.SurveyRequest;
import com.inner_circle.survey.dto.response.OptionResponse;
import com.inner_circle.survey.dto.response.QuestionResponse;
import com.inner_circle.survey.dto.response.SurveyResponse;
import com.inner_circle.survey.entity.request.Option;
import com.inner_circle.survey.entity.request.Question;
import com.inner_circle.survey.entity.request.Survey;
import com.inner_circle.survey.repository.OptionRepository;
import com.inner_circle.survey.repository.QuestionRepository;
import com.inner_circle.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

  private final SurveyRepository surveyRepository;
  private final QuestionRepository questionRepository;
  private final OptionRepository optionRepository;

  public List<SurveyResponse> getAllSurvey() {
    List<Survey> surveys = surveyRepository.findAll();
    return surveys.stream().map(this::responseFromSurvey).toList();
  }

  public SurveyResponse getSurvey(Long surveyId) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow();
    return responseFromSurvey(survey);
  }

  @Transactional
  public SurveyResponse saveSurvey(SurveyRequest surveyRequest) {
    Survey survey = surveyRepository.save(surveyFromRequest(surveyRequest));

    surveyRequest.questions().forEach(questionRequest -> {
      Question question = questionRepository.save(questionFromRequest(survey, questionRequest));
      questionRequest.options().forEach(optionRequest ->
          optionRepository.save(optionFromRequest(question, optionRequest))
      );
    });
    return responseFromSurvey(survey);
  }

  @Transactional
  public SurveyResponse updateSurvey(Long surveyId, SurveyRequest surveyRequest) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow();
    if (
        !survey.getTitle().equals(surveyRequest.title()) ||
            !survey.getDescription().equals(surveyRequest.description())
    ) {
      survey.update(surveyRequest.title(), surveyRequest.description());
    }

    List<Question> questions = questionRepository.findBySurveyAndLatestTrueOrderByOrder(survey);
    questions.sort(Comparator.comparing(Question::getOrder));

    List<QuestionRequest> questionRequests = surveyRequest.questions();
    questionRequests.sort(Comparator.comparing(QuestionRequest::order));

    for (int i = 0; i < Math.min(questions.size(), questionRequests.size()); i++) {
      Question question = questions.get(i);
      QuestionRequest questionRequest = questionRequests.get(i);
      updateQuestion(question, questionRequest);
    }

    return responseFromSurvey(survey);
  }

  private void updateQuestion(Question question, QuestionRequest questionRequest) {
    List<Option> options = optionRepository.findByQuestionAndLatestTrueOrderByOrder(question);
    options.sort(Comparator.comparing(Option::getOrder));

    List<OptionRequest> optionRequests = questionRequest.options();
    optionRequests.sort(Comparator.comparing(OptionRequest::order));

    if (
        !question.getTitle().equals(questionRequest.title()) ||
            !question.getDescription().equals(questionRequest.description()) ||
            question.isRequired() != questionRequest.required() ||
            question.getType() != questionRequest.type()
    ) {
      question.setLatest(false);
      Question latestQuestion = new Question(
          question.getSurvey(),
          questionRequest.type(),
          questionRequest.title(),
          questionRequest.description(),
          questionRequest.required(),
          questionRequest.order()
      );
      question = questionRepository.save(latestQuestion);
    }

    for (int i = 0; i < Math.min(options.size(), optionRequests.size()); i++) {
      Option option = options.get(i);
      OptionRequest optionRequest = optionRequests.get(i);
      updateOption(question, option, optionRequest);
    }
  }

  private void updateOption(Question question, Option option, OptionRequest optionRequest) {
    if (
        !option.getAnswer().equals(optionRequest.answer()) ||
            option.getOrder() != optionRequest.order()
    ) {
      option.setLatest(false);
      Option latestOption = new Option(
          question,
          optionRequest.answer(),
          optionRequest.order()
      );
      optionRepository.save(latestOption);
      return;
    }
    option.setQuestion(question);
  }

  private SurveyResponse responseFromSurvey(Survey survey) {
    List<QuestionResponse> questionResponses = questionRepository.findBySurveyAndLatestTrueOrderByOrder(survey)
        .stream()
        .map(this::responseFromQuestion)
        .toList();

    return new SurveyResponse(
        survey.getId(), survey.getTitle(), survey.getDescription(), questionResponses
    );
  }

  private QuestionResponse responseFromQuestion(Question question) {
    List<OptionResponse> optionResponses = optionRepository.findByQuestionAndLatestTrueOrderByOrder(question)
        .stream()
        .map(this::responseFromOption)
        .toList();

    return new QuestionResponse(
        question.getId(),
        question.getType(),
        question.getOrder(),
        question.getTitle(),
        question.getDescription(),
        question.isRequired(),
        optionResponses
    );
  }

  private OptionResponse responseFromOption(Option option) {
    return new OptionResponse(option.getId(), option.getOrder(), option.getAnswer());
  }

  private Survey surveyFromRequest(SurveyRequest request) {
    return new Survey(request.title(), request.description());
  }

  private Question questionFromRequest(Survey survey, QuestionRequest request) {
    return new Question(
        survey,
        request.type(),
        request.title(),
        request.description(),
        request.required(),
        request.order()
    );
  }

  private Option optionFromRequest(Question question, OptionRequest request) {
    return new Option(question, request.answer(), request.order());
  }
}
