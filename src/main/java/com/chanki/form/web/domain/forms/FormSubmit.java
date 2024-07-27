package com.chanki.form.web.domain.forms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@IdClass(FormSubmit.class)
@Table(name = "form_submit_list")
public class FormSubmit {
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
  @Column(name = "answer_sequence")
  private long answerSequence;

  @Column(name = "answer")
  private String answer;

  @Builder
  public FormSubmit(long formId, long version, long sequence, long answerSequence, String answer) {
    this.formId = formId;
    this.version = version;
    this.sequence = sequence;
    this.answerSequence = answerSequence;
    this.answer = answer;
  }
}
