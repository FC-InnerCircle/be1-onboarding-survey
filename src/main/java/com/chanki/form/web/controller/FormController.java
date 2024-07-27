package com.chanki.form.web.controller;

import com.chanki.form.web.dto.FormEditRequestDto;
import com.chanki.form.web.dto.FormSubmitDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chanki.form.web.domain.forms.Form;
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

  @GetMapping("/api/form/{formId}")
  public Form save(@PathVariable("formId") long formId) {
    return formService.selectForm(formId);
  }

  @PutMapping("/api/form")
  public long edit(@RequestBody FormEditRequestDto formEditRequestDto) {
    return formService.editForm(formEditRequestDto);
  }

  @PostMapping("/api/form/{formId}")
  public long submit(@PathVariable("formId") long formId, @RequestBody FormSubmitDto formSubmitDto) {
    return formService.submitForm(formId, formSubmitDto);
  }
}