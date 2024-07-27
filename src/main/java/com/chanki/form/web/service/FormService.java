package com.chanki.form.web.service;

import com.chanki.form.web.domain.forms.Form;
import com.chanki.form.web.domain.forms.FormItem;
import com.chanki.form.web.domain.forms.FormItemOption;
import com.chanki.form.web.domain.forms.FormItemOptionRepository;
import com.chanki.form.web.domain.forms.FormItemRepository;
import com.chanki.form.web.domain.forms.FormRepository;
import com.chanki.form.web.domain.forms.FormSubmit;
import com.chanki.form.web.domain.forms.FormSubmitRepository;
import com.chanki.form.web.dto.FormCreateRequestDto;
import com.chanki.form.web.dto.FormEditRequestDto;
import com.chanki.form.web.dto.FormSubmitDto;
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
  private final FormSubmitRepository formSubmitRepository;

//  public boolean isValidSubmitForm(List<FormSubmit> formSubmits, List<FormItem> formItems) {
//    if(formSubmits.size() != formItems.size()) return false;
//    for(int i = 0; i < formSubmits.size(); i++) {
//      FormSubmit formSubmit = formSubmits.get(i);
//      FormItem = formItems.
//    }
//  }
  @Transactional
  public Form createForm(FormCreateRequestDto formCreateRequestDto) {
    Form form = formRepository.save(formCreateRequestDto.toEntity());
    List<FormItem> formItems = formCreateRequestDto.getFormItemList(form);

    for(FormItem _formItem: formItems) {
      FormItem formItem = formItemRepository.save(_formItem);
      List<FormItemOption> formItemOptions = formCreateRequestDto.getFormItemOptionList(formItem);
      formItemOptionRepository.saveAll(formItemOptions);
    }
    return form;
  }

  @Transactional
  public long editForm(FormEditRequestDto formEditRequestDto) {
//    Form form = formRepository.findById(formEditRequestDto.getFormId())
//        .orElseThrow(() -> new IllegalArgumentException("Form does not exist"));
//    long getNextSequence = formItemRepository.findNextVersion(form.getFormId());
//
//    for (int i = 0; i < formEditRequestDto.getFormItems().size(); i++) {
//      FormItem formItem = formEditRequestDto.getFormItems().get(i).toEntity(form.getFormId(), getNextSequence, i + 1);
//      formItemRepository.save(formItem);
//    }
//
//    return form.getFormId();
    return 1;
  }

  @Transactional
  public long submitForm(long formId, FormSubmitDto formSubmitDto) {
    List<FormSubmit> answerList = formSubmitDto.getFormSubmitEntityList(formId);
    Form form = formRepository.findById(formId)
        .orElseThrow(() -> new IllegalArgumentException("Form does not exist"));

    long currentVersion = formItemRepository.findLatestVersion(formId);
    List<FormItem> formItems = formItemRepository.findLatestVersionFormItemList(formId);
    List<FormSubmit> formSubmits = formSubmitRepository.saveAll(answerList);
    return form.getFormId();
  }

  // ##TODO 전체 FORM 형태 조회 테스트를 위한 Service(추후 제거)
  public Form selectForm(long formId) {
    Optional<Form> form = formRepository.findById(formId);
    return form.get();
  }
}
