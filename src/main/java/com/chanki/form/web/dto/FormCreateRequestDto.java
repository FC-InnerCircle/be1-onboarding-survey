package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.Form;
import com.chanki.form.web.domain.forms.FormItemOption;
import java.util.ArrayList;
import java.util.List;

import com.chanki.form.web.domain.forms.FormItem;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormCreateRequestDto {

  private String title;
  private String description;
  private List<FormItemCreateRequestDto> formItems;

  public List<FormItem> getFormItemList(Form form) {
    AtomicLong atomicLong = new AtomicLong();

    return this.formItems
        .stream()
        .map(formItem -> formItem.toEntity(form, 1, atomicLong.incrementAndGet()))
        .toList();
  }

  public List<FormItemOption> getFormItemOptionList(FormItem formItem) {

    return this.formItems
        .stream()
        .flatMap(_formItem -> _formItem.getFormItemOptionList(formItem))
        .toList();
  }


  public Form toEntity() {
    return Form.builder()
        .title(this.title)
        .description(this.description)
        .build();
  }
}
