package com.chanki.form.domain.forms;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "form")
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "form_id")
	private Long formID;
		
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 500, nullable = false)
	private String description;
	
	@Transient
	private List<FormItem> formItems;
	
	@Builder
	public Form(String title, String description, List<FormItem> formItems) {
		this.title = title;
		this.description = description;
		this.formItems = formItems;
	}
}
