package com.inner_circle.survey.service;

import com.inner_circle.survey.dto.request.*;
import com.inner_circle.survey.dto.response.*;
import com.inner_circle.survey.entity.request.Option;
import com.inner_circle.survey.entity.request.Question;
import com.inner_circle.survey.entity.request.Survey;
import com.inner_circle.survey.entity.response.Respondent;
import com.inner_circle.survey.entity.response.ResponseOption;
import com.inner_circle.survey.entity.response.UserResponse;
import com.inner_circle.survey.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

  private final SurveyRepository surveyRepository;
  private final QuestionRepository questionRepository;
  private final OptionRepository optionRepository;
  private final RespondentRepository respondentRepository;
  private final UserResponseRepository userResponseRepository;
  private final ResponseOptionRepository responseOptionRepository;

  public List<SurveyResponse> getAllSurvey() {
    List<Survey> surveys = surveyRepository.findAll();
    return surveys.stream().map(this::responseFromSurvey).toList();
  }

  public SurveyResponse getSurvey(Long surveyId) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow();
    return responseFromSurvey(survey);
  }

  public List<RespondentResponse> getAnswers(Long surveyId) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow();
    List<Respondent> respondents = respondentRepository.findBySurvey(survey);

    return respondents.stream().map(respondent -> {
      List<UserResponseResponse> userResponseResponses = userResponseRepository.findByRespondent(respondent).stream().map(userResponse -> {
        List<ResponseOptionResponse> optionResponses = responseOptionRepository.findByUserResponse(userResponse).stream()
            .map(responseOption -> new ResponseOptionResponse(responseOption.getId(), responseOption.getOption().getAnswer()))
            .collect(Collectors.toList());

        return new UserResponseResponse(
            userResponse.getId(),
            userResponse.getQuestion().getTitle(),
            userResponse.getQuestion().getDescription(),
            userResponse.getAnswer(),
            optionResponses
        );
      }).collect(Collectors.toList());

      return new RespondentResponse(respondent.getId(), respondent.getName(), userResponseResponses);
    }).collect(Collectors.toList());
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
    if (!survey.getTitle().equals(surveyRequest.title()) || !survey.getDescription().equals(surveyRequest.description())) {
      survey.update(surveyRequest.title(), surveyRequest.description());
    }

    List<Question> questions = questionRepository.findBySurveyAndLatestTrueOrderByOrder(survey);
    List<QuestionRequest> questionRequests = surveyRequest.questions();

    updateQuestions(survey, questions, questionRequests);

    return responseFromSurvey(survey);
  }

  @Transactional
  public Long answerToSurvey(Long surveyId, RespondentRequest respondentRequest) {
    Survey survey = surveyRepository.findById(surveyId).orElseThrow();
    Respondent respondent = new Respondent(survey, respondentRequest.name());
    respondentRepository.save(respondent);

    respondentRequest.responses().forEach(userResponseRequest -> {
      Question question = questionRepository.findById(userResponseRequest.id()).orElseThrow();
      UserResponse userResponse = new UserResponse(respondent, question, userResponseRequest.answer());
      userResponseRepository.save(userResponse);

      userResponseRequest.options().forEach(optionId -> {
        Option option = optionRepository.findById(optionId).orElseThrow();
        ResponseOption responseOption = new ResponseOption(userResponse, option);
        responseOptionRepository.save(responseOption);
      });
    });
    return respondent.getId();
  }

  private void updateQuestions(Survey survey, List<Question> questions, List<QuestionRequest> questionRequests) {
    int maxSize = Math.max(questions.size(), questionRequests.size());

    IntStream.range(0, maxSize).forEach(i -> {
      if (i >= questions.size()) {
        createNewQuestionWithNewOptions(survey, questionRequests.get(i));
      } else if (i >= questionRequests.size()) {
        setQuestionAndOptionsAsOld(questions.get(i));
      } else {
        updateExistingQuestion(questions.get(i), questionRequests.get(i));
      }
    });
  }

  private void createNewQuestionWithNewOptions(Survey survey, QuestionRequest questionRequest) {
    Question newQuestion = questionRepository.save(questionFromRequest(survey, questionRequest));
    questionRequest.options().forEach(optionRequest ->
        optionRepository.save(optionFromRequest(newQuestion, optionRequest))
    );
  }

  private void setQuestionAndOptionsAsOld(Question question) {
    question.setLatest(false);
    optionRepository.findByQuestionAndLatestTrueOrderByOrder(question)
        .forEach(option -> option.setLatest(false));
  }

  private void updateExistingQuestion(Question question, QuestionRequest questionRequest) {
    List<Option> options = optionRepository.findByQuestionAndLatestTrueOrderByOrder(question);
    List<OptionRequest> optionRequests = questionRequest.options();

    if (isQuestionUpdated(question, questionRequest)) {
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
    updateOptions(question, options, optionRequests);
  }

  private boolean isQuestionUpdated(Question question, QuestionRequest questionRequest) {
    return !question.getTitle().equals(questionRequest.title()) ||
        !question.getDescription().equals(questionRequest.description()) ||
        question.isRequired() != questionRequest.required() ||
        question.getType() != questionRequest.type();
  }

  private void updateOptions(Question question, List<Option> options, List<OptionRequest> optionRequests) {
    int maxSize = Math.max(options.size(), optionRequests.size());

    IntStream.range(0, maxSize).forEach(i -> {
      if (i >= options.size()) {
        optionRepository.save(new Option(question, optionRequests.get(i).answer(), optionRequests.get(i).order()));
      } else if (i >= optionRequests.size()) {
        options.get(i).setLatest(false);
      } else {
        updateOption(question, options.get(i), optionRequests.get(i));
      }
    });
  }

  private void updateOption(Question question, Option option, OptionRequest optionRequest) {
    if (!option.getAnswer().equals(optionRequest.answer()) || option.getOrder() != optionRequest.order()) {
      option.setLatest(false);
      Option latestOption = new Option(question, optionRequest.answer(), optionRequest.order());
      optionRepository.save(latestOption);
    } else {
      option.setQuestion(question);
    }
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
