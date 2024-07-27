package com.chanki.form.web.domain.forms;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FormItemOptionId implements Serializable {

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
}
