package com.chanki.form.web.dto;

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

}
