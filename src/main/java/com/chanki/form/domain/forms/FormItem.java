package com.chanki.form.domain.forms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "form_id", referencedColumnName = "form_id"),
		@JoinColumn(name = "version", referencedColumnName = "version"),
		@JoinColumn(name = "sequence", referencedColumnName = "sequence")
	})
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
