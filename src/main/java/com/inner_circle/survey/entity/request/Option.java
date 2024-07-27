package com.inner_circle.survey.entity.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne
  private Question question;

  @Column(name = "option_order")
  private int order;

  @Setter
  private boolean latest;

  private String answer;

  public Option(Question question, String answer, int order) {
    this.question = question;
    this.answer = answer;
    this.order = order;
    this.latest = true;
  }
}
