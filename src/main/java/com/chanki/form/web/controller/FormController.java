package com.chanki.form.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chanki.form.domain.forms.Form;
import com.chanki.form.web.dto.FormCreateRequestDto;
import com.chanki.form.web.service.FormService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FormController {
	
	private final FormService formService;
	
	@PostMapping("/api/create")
	public Form save(@RequestBody FormCreateRequestDto formCreateRequestDto) {
		return formService.createForm(formCreateRequestDto);
	}
}