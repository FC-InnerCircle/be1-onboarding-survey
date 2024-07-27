package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormItemOptionCreateRequestDto {

  private String description;


  public FormItemOption toEntity(FormItem formItem, long optionSequence) {
    return FormItemOption.builder()
        .formItem(formItem)
        .optionSequence(optionSequence)
        .description(this.description)
        .build();
  }

}
