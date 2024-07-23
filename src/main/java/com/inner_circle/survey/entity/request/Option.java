package com.inner_circle.survey.entity.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Question question;

  @Column(name = "option_order")
  private int order;

  private String answer;
  private boolean latest;

  public Option(Question question, String answer, int order) {
    this.question = question;
    this.answer = answer;
    this.order = order;
    this.latest = true;
  }
}
