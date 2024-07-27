package com.chanki.form.web.domain.forms;

import jakarta.persistence.IdClass;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;

@ToString
@Getter
@NoArgsConstructor
@Entity
@IdClass(FormId.class)
@Table(name = "form")
public class Form {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "form_id")
  private Long formId;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(length = 500, nullable = false)
  private String description;

  @Builder
  public Form(String title, String description) {
    this.title = title;
    this.description = description;
  }
}
