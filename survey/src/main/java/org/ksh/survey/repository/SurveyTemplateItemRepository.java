package org.ksh.survey.repository;

import org.ksh.survey.entity.SurveyTemplateItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyTemplateItemRepository extends JpaRepository<SurveyTemplateItem, Long> {
	List<SurveyTemplateItem> findAllBySurveyTemplateId(Long id);

}
