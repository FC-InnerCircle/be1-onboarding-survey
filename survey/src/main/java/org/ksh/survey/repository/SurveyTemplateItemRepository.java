package org.ksh.survey.repository;

import org.ksh.survey.entity.SurveyTemplateItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyTemplateItemRepository extends JpaRepository<SurveyTemplateItem, Long> {
}
