package com.inner_circle.survey.entity.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Survey survey;

  @Enumerated(value = EnumType.STRING)
  private AnswerType type;

  private String title;
  private String description;
  private boolean required;
  private boolean latest;

  public Question(Survey survey, AnswerType type, String title, String description, boolean required) {
    this.survey = survey;
    this.type = type;
    this.title = title;
    this.description = description;
    this.required = required;
    this.latest = true;
  }
}
