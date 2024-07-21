package com.chanki.form.domain.forms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@IdClass(FormItemOptionId.class)
@Table(name = "form_item_option")
@Entity
public class FormItemOption {
	@Id
	@Column(name = "form_id")
	@JoinColumn(name = "form_id")
	private long formId;
	
	@Id
	@JoinColumn(name = "version")
	private long version;
	
	@Id
	@JoinColumn(name = "sequence")
	private long sequence;
	
	@Id
	@Column(name="option_sequence")
	private long optionSequence;
	
	@Column(length = 500)
	private String description;
	
	@Builder
	public FormItemOption(long formId, long version, long sequence, long optionSequence, String description, FormItemType type) {
		this.formId = formId;
		this.version = version;
		this.sequence = sequence;
		this.optionSequence = optionSequence;
		this.description = description;
	}
}
