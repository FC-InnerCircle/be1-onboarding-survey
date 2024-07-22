package com.chanki.form.web.domain.forms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormItemRepository extends JpaRepository<FormItem, FormItemId> {

  @Query("SELECT MAX(fi.version) + 1 AS version FROM FormItem fi WHERE fi.formId =:formId GROUP BY fi.formId")
  long findNextVersion(@Param("formId") long formId);
}
