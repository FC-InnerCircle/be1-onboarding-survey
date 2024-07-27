package com.inner_circle.survey.entity.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(name = "question_order")
  private int order;

  @Setter
  private boolean latest;

  private String title;
  private String description;
  private boolean required;

  public Question(Survey survey, AnswerType type, String title, String description, boolean required, int order) {
    this.survey = survey;
    this.type = type;
    this.title = title;
    this.description = description;
    this.required = required;
    this.order = order;
    this.latest = true;
  }
}
