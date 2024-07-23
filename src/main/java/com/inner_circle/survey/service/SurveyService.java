package com.inner_circle.survey.service;

import com.inner_circle.survey.dto.request.SurveyRequest;
import com.inner_circle.survey.dto.response.SurveyResponse;
import com.inner_circle.survey.repository.OptionRepository;
import com.inner_circle.survey.repository.QuestionRepository;
import com.inner_circle.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

  private final SurveyRepository surveyRepository;
  private final QuestionRepository questionRepository;
  private final OptionRepository optionRepository;

  public List<SurveyResponse> getAllSurvey() {
    return null;
  }

  public SurveyResponse getSurvey(Long surveyId) {
    return null;
  }

  @Transactional
  public SurveyResponse saveSurvey(SurveyRequest surveyRequest) {
    return null;
  }

  @Transactional
  public SurveyResponse updateSurvey(SurveyRequest surveyRequest) {
    return null;
  }
}
