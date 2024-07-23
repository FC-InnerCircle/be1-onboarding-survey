package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormItemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormItemCreateRequestDto {

  private String description;
  private boolean required;
  private FormItemType type;
  private List<FormItemOptionCreateRequestDto> formItemOptions;

  public FormItem toEntity(long formId, long version, long sequence) {
    AtomicLong atomicLong = new AtomicLong();

    List<FormItemOption> formItemOptions = Optional.ofNullable(this.formItemOptions).orElseGet(ArrayList::new).stream()
        .map(option -> option.toEntity(formId, version, sequence, atomicLong.incrementAndGet()))
        .toList();

    return FormItem.builder()
        .formId(formId)
        .version(version)
        .sequence(sequence)
        .description(this.description)
        .type(this.type)
        .formItemOptions(formItemOptions)
        .required(this.required)
        .build();
  }
}
