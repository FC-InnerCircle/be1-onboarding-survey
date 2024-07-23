package com.inner_circle.survey.service;

import com.inner_circle.survey.dto.request.OptionRequest;
import com.inner_circle.survey.dto.request.QuestionRequest;
import com.inner_circle.survey.dto.request.SurveyRequest;
import com.inner_circle.survey.dto.response.SurveyResponse;
import com.inner_circle.survey.entity.request.AnswerType;
import com.inner_circle.survey.entity.request.Option;
import com.inner_circle.survey.entity.request.Question;
import com.inner_circle.survey.entity.request.Survey;
import com.inner_circle.survey.repository.OptionRepository;
import com.inner_circle.survey.repository.QuestionRepository;
import com.inner_circle.survey.repository.SurveyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class SurveyServiceTest {

  @Autowired
  private SurveyRepository surveyRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private OptionRepository optionRepository;

  @Autowired
  private SurveyService surveyService;

  @PersistenceContext
  EntityManager entityManager;

  @BeforeEach
  void init() {
    Survey survey = new Survey("test survey", "test survey desc");
    entityManager.persist(survey);

    Question question1 = new Question(
        survey, AnswerType.SHORT, "test question1", "test question desc 1", true, 1
    );
    entityManager.persist(question1);
    Question question2 = new Question(
        survey, AnswerType.SINGLE, "test question2", "test question desc 2", false, 2
    );
    entityManager.persist(question2);
    entityManager.flush();

    Option option1 = new Option(question2, "test option1", 1);
    entityManager.persist(option1);
    Option option2 = new Option(question2, "test option2", 2);
    entityManager.persist(option2);
  }

  @AfterEach
  void destroy() {
    optionRepository.deleteAll();
    questionRepository.deleteAll();
    surveyRepository.deleteAll();
  }

  @DisplayName("전체 설문을 조회한다")
  @Test
  void getAllSurvey() {
    // given & when
    List<SurveyResponse> surveys = surveyService.getAllSurvey();

    // then
    assertThat(surveys.size()).isEqualTo(1);
    assertThat(surveys.get(0).description()).isEqualTo("test survey desc");
    assertThat(surveys.get(0).questions().size()).isEqualTo(2);
  }

  @DisplayName("단일 설문을 조회한다")
  @Test
  void getSurvey() {
    // given & when
    List<Survey> surveys = surveyRepository.findAll();
    SurveyResponse survey = surveyService.getSurvey(surveys.get(0).getId());

    // then
    assertThat(survey.title()).isEqualTo("test survey");
    assertThat(survey.questions().size()).isEqualTo(2);
  }

  @DisplayName("전달받은 설문을 저장한다")
  @Test
  void saveSurvey() {
    // given
    OptionRequest option = new OptionRequest("option", 1);
    QuestionRequest question = new QuestionRequest(
        "question", "desc", AnswerType.SINGLE, 1, true, List.of(option)
    );
    SurveyRequest survey = new SurveyRequest("survey", "survey desc", List.of(question));

    // when
    SurveyResponse surveyResponse = surveyService.saveSurvey(survey);
    entityManager.flush();

    // then
    List<Survey> surveys = surveyRepository.findAll();
    assertThat(surveys.size()).isEqualTo(2);
    assertThat(surveyResponse.questions().get(0).title()).isEqualTo("question");
    assertThat(surveyResponse.questions().get(0).options().get(0).answer()).isEqualTo("option");
  }

  @DisplayName("설문 내용을 업데이트한다")
  @Test
  void updateSurvey() {
    // given
    OptionRequest option = new OptionRequest("option", 1);
    QuestionRequest question = new QuestionRequest(
        "question", "desc", AnswerType.SINGLE, 1, true, List.of(option)
    );
    SurveyRequest survey = new SurveyRequest("survey", "survey desc", List.of(question));

    // when
    SurveyResponse surveyResponse = surveyService.updateSurvey(survey);
    entityManager.flush();

    // then
    assertThat(surveyResponse.questions().get(0).title()).isEqualTo("question");
    assertThat(surveyResponse.questions().get(0).options().get(0).answer()).isEqualTo("option");
  }
}
