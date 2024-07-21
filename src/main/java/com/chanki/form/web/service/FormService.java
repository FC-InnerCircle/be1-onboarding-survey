package com.chanki.form.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chanki.form.domain.forms.Form;
import com.chanki.form.domain.forms.FormItem;
import com.chanki.form.domain.forms.FormItemOption;
import com.chanki.form.domain.forms.FormItemOptionRepository;
import com.chanki.form.domain.forms.FormItemRepository;
import com.chanki.form.domain.forms.FormRepository;
import com.chanki.form.web.dto.FormCreateRequestDto;

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
	
	public List<FormItemOption> getListWithSetParentItemInfo(FormItem formItem, List<FormItemOption> formItemOptions) {
		long formId = formItem.getFormId();
		long version = formItem.getVersion();
		long sequence = formItem.getSequence();
		
		// ## TODO 없을 시 예외처리 했지만, 해당 로직 먼저 validation 로직 처리 할 수 있게 처리
		if(formItemOptions == null) return new ArrayList<>();
		
		// ## TODO Setter 를 사용시 어디서 값이 변경되는지 알기 어려울거 같아 안 쓰면서 개발중인데, 이게 맞는건지 모르겠다.
		AtomicLong index = new AtomicLong();
		return formItemOptions.stream()
				.map(option -> FormItemOption.builder().formId(formId).version(version).sequence(sequence).optionSequence(index.getAndIncrement() + 1).description(option.getDescription()).build()).collect(Collectors.toList());
	}
	
	
	@Transactional
	public Form createForm(FormCreateRequestDto formCreateRequestDto) {
		Form form = Form.builder()
				.description(formCreateRequestDto.getDescription())
				.title(formCreateRequestDto.getTitle()).build();
		
		Form insertedForm = formRepository.save(form);
		List<FormItem> formItems = formCreateRequestDto.getFormItems();

		// ## TODO 수정 - 다른 방식 조사 / 수정
		for(int i = 0; i < formItems.size(); i++) {
			FormItem formItem = formItems.get(i);
			FormItem inserted = formItemRepository.save(FormItem.builder().formId(insertedForm.getFormID()).version(1L).sequence(i + 1).description(formItem.getDescription()).type(formItem.getType()).build());
			List<FormItemOption> formItemOptions = this.getListWithSetParentItemInfo(inserted, formItem.getFormItemOptions());
			formItemOptionRepository.saveAll(formItemOptions);
		}

		return insertedForm;
	}
	
	// ##TODO 전체 FORM 형태 조회 테스트를 위한 Service(추후 제거)
	@Transactional
	public Form selectForm(long formId) {
		Optional<Form> form = formRepository.findById(formId);
		Form form1 = form.get();
		return form1;
	}
}
