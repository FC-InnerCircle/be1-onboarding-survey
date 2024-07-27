package com.inner_circle.survey.entity.response;

import com.inner_circle.survey.entity.request.Survey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Respondent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Survey survey;

  private String name;

  public Respondent(Survey survey, String name) {
    this.survey = survey;
    this.name = name;
  }
}
