package com.chanki.form.web.domain.forms;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
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
  private long formId;

  @Id
  private long version;

  @Id
  private long sequence;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumns({
      @JoinColumn(name = "form_id", referencedColumnName = "form_id", insertable = false, updatable = false),
      @JoinColumn(name = "version", referencedColumnName = "version", insertable = false, updatable = false),
      @JoinColumn(name = "sequence", referencedColumnName = "sequence", insertable = false, updatable = false)
  })
  private FormItem formItem;

  @Id
  @Column(name = "option_sequence")
  private long optionSequence;

  @Column(length = 500)
  private String description;

  @Builder
  public FormItemOption(FormItem formItem,  long optionSequence, String description) {
    this.formItem = formItem;
    this.optionSequence = optionSequence;
    this.description = description;
  }
}
