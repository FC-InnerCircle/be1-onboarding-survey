package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.Form;
import java.util.List;

import com.chanki.form.web.domain.forms.FormItem;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormCreateRequestDto {

  private String title;
  private String description;
  private List<FormItemCreateRequestDto> formItems;

  public List<FormItem> getFormItemList(long formId, long version, long sequence) {
    return this.formItems
        .stream()
        .map(formItem -> formItem.toEntity(formId, version, sequence))
        .toList();
  }

  public Form toEntity() {
    return Form.builder()
        .title(this.title)
        .description(this.description)
        .build();
  }
}
