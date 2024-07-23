package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.FormItemOption;
import jakarta.persistence.Column;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormItemOptionCreateRequestDto {

  private String description;

  public FormItemOption toEntity(long formId, long version, long sequence, long optionSequence) {
    return FormItemOption.builder()
        .formId(formId)
        .version(version)
        .sequence(sequence)
        .optionSequence(optionSequence)
        .description(this.description)
        .build();
  }

}
