package com.chanki.form.domain.forms;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class FormItemId implements Serializable {
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "form_id")
	private long formId;
	
	@EqualsAndHashCode.Include
	@Id
	private long version;
	
	@EqualsAndHashCode.Include
	@Id
	private long sequence;
}
