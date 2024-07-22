package com.chanki.form.web.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class FormEditRequestDto {

  private long formId;
  private String title;
  private String description;
  private List<FormItemCreateRequestDto> formItems;
}
