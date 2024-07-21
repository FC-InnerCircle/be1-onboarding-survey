package com.chanki.form.web.dto;

import java.util.List;

import com.chanki.form.domain.forms.FormItem;
import com.chanki.form.domain.forms.Form;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class FormCreateRequestDto {
	private String title;
	private String description;
	private List<FormItem> formItems;
	
	@Builder
	public FormCreateRequestDto(String title, String description, List<FormItem> formItems) {
		this.title = title;
		this.description = description;
		this.formItems = formItems;
	}
	
	public Form toEntity() {
		return Form.builder()
				.title(title)
				.description(description)
				.build();
	}	
}
