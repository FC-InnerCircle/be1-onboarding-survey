package com.chanki.form.web.service;

import com.chanki.form.web.dto.FormEditRequestDto;
import com.chanki.form.web.dto.FormItemCreateRequestDto;
import com.chanki.form.web.dto.FormItemOptionCreateRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chanki.form.web.domain.forms.Form;
import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormItemOptionRepository;
import com.chanki.form.web.domain.forms.FormItemRepository;
import com.chanki.form.web.domain.forms.FormRepository;
import com.chanki.form.web.dto.FormCreateRequestDto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// ## TODO 식별관계로 만들어, 뭔가 복잡한거 같은데.. 
// ## TODO 비식별관계로 변경해야 하는지...

@RequiredArgsConstructor
@Service
public class FormService {

  private final FormRepository formRepository;
  private final FormItemRepository formItemRepository;
  private final FormItemOptionRepository formItemOptionRepository;

  public List<FormItemOption> getListWithSetParentItemInfo(FormItem formItem,
      List<FormItemOptionCreateRequestDto> formItemOptions) {
    long formId = formItem.getFormId();
    long version = formItem.getVersion();
    long sequence = formItem.getSequence();

    // ## TODO 없을 시 예외처리 했지만, 해당 로직 먼저 validation 로직 처리 할 수 있게 처리
    if (formItemOptions == null) {
      return new ArrayList<>();
    }

    AtomicLong index = new AtomicLong();
    return formItemOptions.stream()
        .map(option -> FormItemOption.builder().formId(formId).version(version).sequence(sequence)
            .optionSequence(index.getAndIncrement() + 1).description(option.getDescription())
            .build()).collect(Collectors.toList());
  }

  @Transactional
  public Form createForm(FormCreateRequestDto formCreateRequestDto) {
    Form form = Form.builder()
        .description(formCreateRequestDto.getDescription())
        .title(formCreateRequestDto.getTitle()).build();

    Form insertedForm = formRepository.save(form);
    List<FormItemCreateRequestDto> formItems = formCreateRequestDto.getFormItems();

    for (int i = 0; i < formItems.size(); i++) {
      FormItemCreateRequestDto formItem = formItems.get(i);
      FormItem inserted = formItemRepository.save(
          FormItem.builder().formId(insertedForm.getFormID()).version(1L).sequence(i + 1)
              .description(formItem.getDescription()).type(formItem.getType()).build());
      List<FormItemOption> formItemOptions = this.getListWithSetParentItemInfo(inserted,
          formItem.getFormItemOptions());
      formItemOptionRepository.saveAll(formItemOptions);
    }

    return insertedForm;
  }

  @Transactional
  public long editForm(FormEditRequestDto formEditRequestDto) {
    Form form = formRepository.findById(formEditRequestDto.getFormId())
        .orElseThrow(() -> new IllegalArgumentException("NO_FORM"));

    // ## TODO 폼 수정 시 설명과 제목 업데이트 해줘야 함.
    long nextVersion = formItemRepository.findNextVersion(form.getFormID());

    List<FormItemCreateRequestDto> formItems = formEditRequestDto.getFormItems();

    // ## TODO 수정 - 다른 방식 조사 / 수정
    for (int i = 0; i < formItems.size(); i++) {
      FormItemCreateRequestDto formItem = formItems.get(i);
      FormItem inserted = formItemRepository.save(
          FormItem.builder().formId(formEditRequestDto.getFormId()).version(nextVersion)
              .sequence(i + 1).description(formItem.getDescription()).type(formItem.getType())
              .build());
      List<FormItemOption> formItemOptions = this.getListWithSetParentItemInfo(inserted,
          formItem.getFormItemOptions());
      formItemOptionRepository.saveAll(formItemOptions);
    }

    return form.getFormID();
  }

  // ##TODO 전체 FORM 형태 조회 테스트를 위한 Service(추후 제거)
  public Form selectForm(long formId) {
    Optional<Form> form = formRepository.findById(formId);
    return form.get();
  }
}
