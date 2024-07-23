package com.chanki.form.web.service;

import com.chanki.form.web.domain.forms.Form;
import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormItemOptionRepository;
import com.chanki.form.web.domain.forms.FormItemRepository;
import com.chanki.form.web.domain.forms.FormRepository;
import com.chanki.form.web.dto.FormCreateRequestDto;
import com.chanki.form.web.dto.FormEditRequestDto;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// ## TODO 식별관계로 만들어, 뭔가 복잡한거 같은데.. 
// ## TODO 비식별관계로 변경해야 하는지...

@RequiredArgsConstructor
@Service
public class FormService {

  private final FormRepository formRepository;
  private final FormItemRepository formItemRepository;
  private final FormItemOptionRepository formItemOptionRepository;

  public List<FormItemOption> getListWithSetParentItemInfo(FormItem formItem,
      List<FormItemOption> formItemOptions) {
    long formId = formItem.getFormId();
    long version = formItem.getVersion();
    long sequence = formItem.getSequence();

    // ## TODO 없을 시 예외처리 했지만, 해당 로직 먼저 validation 로직 처리 할 수 있게 처리
    if (formItemOptions == null) {
      return new ArrayList<>();
    }

    AtomicLong index = new AtomicLong();
    return formItemOptions.stream()
        .map(option -> FormItemOption.builder()
            .formId(formId)
            .version(version)
            .sequence(sequence)
            .optionSequence(index.getAndIncrement() + 1)
            .description(option.getDescription())
            .build())
        .toList();
  }

  @Transactional
  public Form createForm(FormCreateRequestDto formCreateRequestDto) {
    Form form = formRepository.save(formCreateRequestDto.toEntity());

    for (int i = 0; i < formCreateRequestDto.getFormItems().size(); i++) {
      FormItem formItem = formCreateRequestDto.getFormItems().get(i).toEntity(form.getFormID(), 1L, i + 1);
      formItemRepository.save(formItem);
    }

    return form;
  }

  @Transactional
  public long editForm(FormEditRequestDto formEditRequestDto) {
    Form form = formRepository.findById(formEditRequestDto.getFormId())
        .orElseThrow(() -> new IllegalArgumentException("Form does not exist"));
    long getNextSequence = formItemRepository.findNextVersion(form.getFormID());

    for (int i = 0; i < formEditRequestDto.getFormItems().size(); i++) {
      FormItem formItem = formEditRequestDto.getFormItems().get(i).toEntity(form.getFormID(), getNextSequence, i + 1);
      formItemRepository.save(formItem);
    }

    return form.getFormID();
  }

  // ##TODO 전체 FORM 형태 조회 테스트를 위한 Service(추후 제거)
  public Form selectForm(long formId) {
    Optional<Form> form = formRepository.findById(formId);
    return form.get();
  }
}
