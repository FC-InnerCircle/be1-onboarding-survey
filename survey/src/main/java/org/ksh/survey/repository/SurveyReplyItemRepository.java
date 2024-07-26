package org.ksh.survey.repository;

import org.ksh.survey.entity.SurveyReplyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyReplyItemRepository extends JpaRepository<SurveyReplyItem, Long> {


    List<SurveyReplyItem> findBySurveyTemplateItemId(Long id);

}
