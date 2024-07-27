package com.chanki.form.web.domain.forms;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FormId {
  @EqualsAndHashCode.Include
  @Id
  @Column(name = "form_id")
  private long formId;
}
