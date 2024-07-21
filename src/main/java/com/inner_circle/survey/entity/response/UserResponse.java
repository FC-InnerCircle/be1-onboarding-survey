package com.inner_circle.survey.entity.response;

import com.inner_circle.survey.entity.request.Survey;
import com.inner_circle.survey.entity.request.Question;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Respondent respondent;

  @ManyToOne
  private Survey survey;

  @ManyToOne
  private Question question;

  private String answer;

  public UserResponse(Respondent respondent, Survey survey, Question question, String answer) {
    this.respondent = respondent;
    this.survey = survey;
    this.question = question;
    this.answer = answer;
  }
}
