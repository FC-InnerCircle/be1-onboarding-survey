package com.chanki.form.domain.forms;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@IdClass(FormItemId.class)
@Table(name = "form_item")
@Entity
public class FormItem {
	@Id
	@Column(name = "form_id")
	@JoinColumn(name = "form_id")
	private long formId;
	
	@Id
	private long version;
	
	@Id
	private long sequence;
	
	@Column(length = 500)
	private String description;
	
	private boolean required;
	
	@Enumerated(EnumType.STRING)
	private FormItemType type;
	
	@Transient
	private List<FormItemOption> formItemOptions;
	
	@Builder
	public FormItem(long formId, long version, long sequence, String description, FormItemType type, List<FormItemOption> formItemOptions) {
		this.formId = formId;
		this.version = version;
		this.sequence = sequence;
		this.description = description;
		this.type = type;
		this.formItemOptions = formItemOptions;
	}
	
}
