package com.chanki.form.web.domain.forms;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormItemRepository extends JpaRepository<FormItem, FormItemId> {

  @Query("SELECT MAX(fi.version) + 1 AS version FROM FormItem fi WHERE fi.formId =:formId GROUP BY fi.formId")
  long findNextVersion(@Param("formId") long formId);

  @Query("SELECT MAX(fi.version) AS version FROM FormItem fi WHERE fi.formId =:formId GROUP BY fi.formId")
  long findLatestVersion(@Param("formId") long formId);

  @Query("""
      SELECT fi
      FROM FormItem fi
      LEFT OUTER JOIN FormItem fi2
      ON fi.formId = fi2.formId
      AND fi.version < fi2.version
      WHERE fi.formId =:formId
      AND fi2.formId IS NULL""" )
  List<FormItem> findLatestVersionFormItemList(@Param("formId") long formId);
}
