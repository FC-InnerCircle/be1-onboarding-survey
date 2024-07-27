package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.Form;
import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormItemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormItemCreateRequestDto {

  private String description;
  private boolean required;
  private FormItemType type;
  private List<FormItemOptionCreateRequestDto> formItemOptions;

  public FormItem toEntity(Form form, long version, long sequence) {
    return FormItem.builder()
        .form(form)
        .version(version)
        .sequence(sequence)
        .description(this.description)
        .required(this.required)
        .type(type)
        .build();
  }

  public Stream<FormItemOption> getFormItemOptionList(FormItem formItem) {
    AtomicLong atomicLong = new AtomicLong();

    return this.formItemOptions
        .stream()
        .map(formItemOption-> formItemOption.toEntity(formItem, atomicLong.incrementAndGet()));
  }
}
