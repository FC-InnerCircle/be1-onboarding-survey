package com.inner_circle.survey.entity.response;

import com.inner_circle.survey.entity.request.Option;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseOption {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private UserResponse userResponse;

  @ManyToOne
  private Option option;

  public ResponseOption(UserResponse userResponse, Option option) {
    this.userResponse = userResponse;
    this.option = option;
  }
}
