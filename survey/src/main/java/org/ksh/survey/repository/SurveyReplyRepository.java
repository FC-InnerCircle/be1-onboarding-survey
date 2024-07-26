package org.ksh.survey.repository;

import org.ksh.survey.entity.SurveyReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyReplyRepository extends JpaRepository<SurveyReply, Long> {
}
