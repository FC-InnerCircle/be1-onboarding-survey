package com.chanki.form.web.dto;

import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormSubmit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormSubmitDto {

  @Getter
  public static class FormAnswer {

    private long version;
    private long sequence;
    private String answer;

    @Builder
    public FormSubmit toEntity(long formId, long answerSequence) {
      return FormSubmit.builder()
          .formId(formId)
          .version(this.version)
          .sequence(this.sequence)
          .answerSequence(answerSequence)
          .answer(this.answer)
          .build();
    }
  }

  public List<FormAnswer> answerList;

  public List<FormSubmit> getFormSubmitEntityList(long formId) {
    AtomicLong atomicLong = new AtomicLong();

    return Optional.ofNullable(this.answerList).orElseGet(ArrayList::new).stream()
        .map(option -> option.toEntity(formId, atomicLong.incrementAndGet()))
        .toList();
  }


}
