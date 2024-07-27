package com.chanki.form.web.domain.forms;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FormSubmitId {

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "form_id")
  private long formId;

  @EqualsAndHashCode.Include
  @Id
  private long version;

  @EqualsAndHashCode.Include
  @Id
  private long sequence;

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "option_sequence")
  private long optionSequence;

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "answer_sequence")
  private long answerSequence;
}
