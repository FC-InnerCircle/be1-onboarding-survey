package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.FormItemType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormItemCreateRequestDto {

  private long sequence;
  private String description;
  private boolean required;
  private FormItemType type;
  private List<FormItemOptionCreateRequestDto> formItemOptions;
}
