package com.chanki.form.web.domain.forms;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import java.util.List;

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
  private long formId;

  @Id
  private long version;

  @Id
  private long sequence;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "form_id", referencedColumnName = "form_id")
  private Form form;


  @Column(length = 500)
  private String description;

  private boolean required;

  @Enumerated(EnumType.STRING)
  private FormItemType type;


  @Builder
  public FormItem(Form form, long version, long sequence, boolean required, String description,
      FormItemType type) {
    this.form = form;
    this.version = version;
    this.sequence = sequence;
    this.required = required;
    this.description = description;
    this.type = type;
  }

}
